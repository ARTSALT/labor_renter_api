package ufersa.com.br.labor_renter.domain.services;

import org.springframework.dao.DataIntegrityViolationException;
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
    private final ContractorRepository contractorRepository;
    private final UserWorkerRepository userWorkerRepository;
    private final JobRepository jobRepository;

    public ContractService(ContractRepository contractRepository, ContractorRepository contractorRepository, UserWorkerRepository userWorkerRepository, JobRepository jobRepository) {
        this.contractRepository = contractRepository;
        this.contractorRepository = contractorRepository;
        this.userWorkerRepository = userWorkerRepository;
        this.jobRepository = jobRepository;
    }


    public List<ContractResponse> findAll() {
        List<Contract> response = contractRepository.findAll();

        return response.stream().map(ContractResponse::new).collect(Collectors.toList());
    }

    public ContractResponse findById(Long id) {
        Contract c = contractRepository.findById(id)
                .orElseThrow(() -> new DataIntegrityViolationException("Id não encontrado"));

        return new ContractResponse(c);
    }

    public ContractResponse create(ContractCreateRequest request) {
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

        return new ContractResponse(contractRepository.save(entity));
    }

    public void delete(Long id) {
        Contract contract = contractRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Contrato com ID " + id + " não encontrado"));

        contractRepository.delete(contract);
    }

    public ContractResponse update(Long id, ContractCreateRequest request) {
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

        return new ContractResponse(contractRepository.save(existingContract));
    }

}
