package ufersa.com.br.labor_renter.domain.services;

import org.springframework.stereotype.Service;
import ufersa.com.br.labor_renter.domain.entities.Contract;
import ufersa.com.br.labor_renter.domain.repositories.ContractRepository;

import java.util.List;

@Service
public class ContractService {
    private final ContractRepository contractRepository;

    public ContractService(ContractRepository contractRepository) {
        this.contractRepository = contractRepository;
    }

    public List<Contract> findAll() {
        return contractRepository.findAll();
    }

    public Contract findById(Long id) throws Exception {
        try {
            return contractRepository.findById(id)
                    .orElseThrow(() -> new Exception("ID inexistente"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Contract create(Contract c) {
        return contractRepository.save(c);
    }

    public void delete(Long id) {
        contractRepository.deleteById(id);
    }

    public Contract update(Long id, Contract c) throws Exception {
        Contract contractExist = contractRepository.findById(id)
                .orElseThrow(() -> new Exception("ID inexistente"));

        contractExist.setContractor(c.getContractor());
        contractExist.setWorker(c.getWorker());
        contractExist.setJob(c.getJob());

        return contractRepository.save(contractExist);
    }
}
