package ufersa.com.br.labor_renter.domain.services;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import ufersa.com.br.labor_renter.api.dto.requests.ContractorCreateRequest;
import ufersa.com.br.labor_renter.api.dto.requests.ContractorRequest;
import ufersa.com.br.labor_renter.api.dto.responses.ContractorResponse;
import ufersa.com.br.labor_renter.domain.entities.Contractor;
import ufersa.com.br.labor_renter.domain.repositories.ContractorRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContractorService {
    private final ContractorRepository contractorRepository;

    public ContractorService(ContractorRepository contractorRepository) {
        this.contractorRepository = contractorRepository;
    }

    public List<ContractorResponse> listALl() {
        List<Contractor> response = contractorRepository.findAll();

        return response.stream().map(ContractorResponse::new).collect(Collectors.toList());
    }

    public ContractorResponse create(ContractorCreateRequest request) {
        if (contractorRepository.existsByEmail(request.getEmail())) {
            throw new DataIntegrityViolationException("Email já cadastrado");
        }

        if (contractorRepository.existsByCpf(request.getCpf())) {
            throw new DataIntegrityViolationException("Cpf já cadastrado");
        }

        if (contractorRepository.existsByPassword(request.getPassword())) {
            throw new DataIntegrityViolationException("Senha já cadastrada");
        }

        Contractor entity = Contractor.builder()
                .name(request.getName())
                .cpf(request.getCpf())
                .email(request.getEmail())
                .password(request.getPassword())
                .contractedElectricians(0)
                .contractedPainters(0)
                .contractedPlumbers(0)
                .build();

        entity = contractorRepository.save(entity);

        return new ContractorResponse(entity);
    }

    public ContractorResponse get(long contractor_id) {
        Contractor entity = contractorRepository.findById(contractor_id).orElseThrow(
                () -> new DataIntegrityViolationException("Contractante Não Encontrado")
        );

        return new ContractorResponse(entity);
    }

    public void delete(long contractor_id) {
        if(contractorRepository.existsById(contractor_id)) {
            throw new DataIntegrityViolationException("Contratante não Existe");
        }

        contractorRepository.deleteById(contractor_id);
    }

    public ContractorResponse update(ContractorRequest patch, long contractor_id) {
        Contractor entity = contractorRepository.findById(contractor_id).orElseThrow(() ->
                new DataIntegrityViolationException("Contractante não Existe")
        );

        entity.setName(patch.getName() != null ? patch.getName() : entity.getName());
        entity.setEmail(patch.getEmail() != null ? patch.getEmail() : entity.getEmail());
        entity.setPassword(patch.getPassword() != null ? patch.getPassword() : entity.getPassword());

        entity = contractorRepository.save(entity);

        return new ContractorResponse(entity);
    }
}
