package ufersa.com.br.labor_renter.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ufersa.com.br.labor_renter.api.dto.requests.ContractCreateRequest;
import ufersa.com.br.labor_renter.api.dto.responses.ContractResponse;
import ufersa.com.br.labor_renter.domain.entities.Contract;
import ufersa.com.br.labor_renter.domain.entities.Contractor;
import ufersa.com.br.labor_renter.domain.entities.Job;
import ufersa.com.br.labor_renter.domain.entities.UserWorker;
import ufersa.com.br.labor_renter.domain.repositories.ContractRepository;
import ufersa.com.br.labor_renter.domain.repositories.ContractorRepository;
import ufersa.com.br.labor_renter.domain.repositories.JobRepository;
import ufersa.com.br.labor_renter.domain.repositories.UserWorkerRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContractService {
    private final ContractRepository contractRepository;

    public ContractService(ContractRepository contractRepository) {
        this.contractRepository = contractRepository;
    }

    @Autowired
    private ContractorRepository contractorRepository;

    @Autowired
    private UserWorkerRepository userWorkerRepository;

    @Autowired
    private JobRepository jobRepository;

    public List<ContractResponse> findAll() {
        List<Contract> response = contractRepository.findAll();

        return response.stream().map(ContractResponse::new).collect(Collectors.toList());
    }

    public Contract findById(Long id) throws Exception {
        try {
            return contractRepository.findById(id)
                    .orElseThrow(() -> new Exception("ID inexistente"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Contract create(ContractCreateRequest request) {
        Contractor contractor = contractorRepository.findById(request.getContractorId())
                .orElseThrow(() -> new IllegalArgumentException("Contratante não encontrado"));

        UserWorker worker = userWorkerRepository.findById(request.getUserWorkerId())
                .orElseThrow(() -> new IllegalArgumentException("Trabalhador não encontrado"));

        Job job = jobRepository.findById(request.getJobId())
                .orElseThrow(() -> new IllegalArgumentException("Trabalho não encontrado"));

        Contract entity = Contract.builder()
                .contractor(contractor)
                .worker(worker)
                .job(job)
                .build();

        return contractRepository.save(entity);
    }

    public void delete(Long id) {
        Contract contract = contractRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Contrato com ID " + id + " não encontrado"));

        contractRepository.delete(contract);
    }

    public Contract update(Long id, ContractCreateRequest request) {
        Contract existingContract = contractRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Contrato com ID " + id + " não encontrado"));

        Contractor contractor = contractorRepository.findById(request.getContractorId())
                .orElseThrow(() -> new IllegalArgumentException("Contratante com ID " + request.getContractorId() + " não encontrado"));

        UserWorker worker = userWorkerRepository.findById(request.getUserWorkerId())
                .orElseThrow(() -> new IllegalArgumentException("Trabalhador com ID " + request.getUserWorkerId() + " não encontrado"));

        Job job = jobRepository.findById(request.getJobId())
                .orElseThrow(() -> new IllegalArgumentException("Trabalho com ID " + request.getJobId() + " não encontrado"));

        existingContract.setContractor(contractor);
        existingContract.setWorker(worker);
        existingContract.setJob(job);

        return contractRepository.save(existingContract);
    }

}
