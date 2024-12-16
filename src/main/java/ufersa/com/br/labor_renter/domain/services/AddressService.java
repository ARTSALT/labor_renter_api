package ufersa.com.br.labor_renter.domain.services;

import jakarta.transaction.Transactional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import ufersa.com.br.labor_renter.api.dto.requests.AddressRequest;
import ufersa.com.br.labor_renter.api.dto.responses.AddressResponse;
import ufersa.com.br.labor_renter.domain.entities.Address;
import ufersa.com.br.labor_renter.domain.entities.Contractor;
import ufersa.com.br.labor_renter.domain.repositories.AddressRepository;
import ufersa.com.br.labor_renter.domain.repositories.ContractorRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class AddressService {
    private final AddressRepository addressRepository;
    private final ContractorRepository contractorRepository;

    public AddressService(AddressRepository addressRepository, ContractorRepository contractorRepository) {
        this.addressRepository = addressRepository;
        this.contractorRepository = contractorRepository;
    }

    public List<AddressResponse> getAll() {
        List<Address> addresses = addressRepository.findAll();
        List<AddressResponse> response = new ArrayList<>();

        for (Address a : addresses) {
            response.add(new AddressResponse(a));
        }

        return response;
    }

    public List<AddressResponse> getAllOfUser(long user_id) {
        Contractor user = contractorRepository.findById(user_id).orElseThrow(() ->
                new DataIntegrityViolationException("Usuário não encontrado")
        );

        List<Address> addresses = user.getAddress();
        List<AddressResponse> response = new ArrayList<>();

        for(Address a : addresses) {
            response.add(new AddressResponse(a));
        }

        return response;
    }

    public AddressResponse get(long address_id) {
        Address entity = addressRepository.findById(address_id).orElseThrow(
                () -> new DataIntegrityViolationException("Endereço não registrado")
        );

        return new AddressResponse(entity);
    }

    @Transactional(rollbackOn = Exception.class)
    public AddressResponse create(long user_id, AddressRequest request) {
        Contractor user = contractorRepository.findById(user_id).orElseThrow(
                () -> new DataIntegrityViolationException("Usuário não existe")
        );

        List<Address> addresses = user.getAddress();


        if(!addresses.isEmpty()) {
            for(Address a : addresses) {
                if(a.getCep().equals(request.getCep())) {
                    throw new DataIntegrityViolationException("Endereço já cadastrado nesse usuário");
                }
            }
        }

        Address entity = Address.builder()
                .cep(request.getCep())
                .street(request.getStreet())
                .houseNumber(request.getHouseNumber())
                .complement(request.getComplement())
                .contractor(user)
                .build();

        entity = addressRepository.save(entity);

        return new AddressResponse(entity);
    }

    @Transactional(rollbackOn = Exception.class)
    public void delete(long address_id) {
        Address address = addressRepository.findById(address_id).orElseThrow(
                () -> new DataIntegrityViolationException("Endereço não registrado")
        );

        addressRepository.delete(address);
    }

    @Transactional(rollbackOn = Exception.class)
    public AddressResponse update(long address_id, AddressRequest request) {
        Address entity = addressRepository.findById(address_id).orElseThrow(
                () -> new DataIntegrityViolationException("Endereço não registrado")
        );

        entity.setCep(request.getCep() != null ? request.getCep() : entity.getCep());
        entity.setStreet(request.getStreet() != null ? request.getStreet() : entity.getStreet());
        entity.setHouseNumber(request.getHouseNumber() > 0 ? request.getHouseNumber() : entity.getHouseNumber());
        entity.setComplement(request.getComplement() != null ? request.getComplement() : entity.getComplement());

        entity = addressRepository.save(entity);

        return new AddressResponse(entity);
    }
}
