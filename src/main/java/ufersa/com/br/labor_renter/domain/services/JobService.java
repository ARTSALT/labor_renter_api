package ufersa.com.br.labor_renter.domain.services;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import ufersa.com.br.labor_renter.api.dto.requests.JobRequest;
import ufersa.com.br.labor_renter.api.dto.responses.JobResponse;
import ufersa.com.br.labor_renter.api.exceptions.ResourceNotFoundException;
import ufersa.com.br.labor_renter.domain.entities.Address;
import ufersa.com.br.labor_renter.domain.entities.Job;
import ufersa.com.br.labor_renter.domain.entities.UserWorker;
import ufersa.com.br.labor_renter.domain.repositories.AddressRepository;
import ufersa.com.br.labor_renter.domain.repositories.JobRepository;
import ufersa.com.br.labor_renter.domain.repositories.UserWorkerRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobService {
    private final JobRepository jobRepository;
    private final UserWorkerRepository userWorkerRepository;

    public JobService(JobRepository jobRepository, UserWorkerRepository userWorkerRepository) {
        this.jobRepository = jobRepository;
        this.userWorkerRepository = userWorkerRepository;
    }

    public List<JobResponse> findAll() {
        List<Job> response = jobRepository.findAll();
        return response.stream().map(JobResponse::new).collect(Collectors.toList());
    }

    public JobResponse findById(Long id) {
        Job j = jobRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Id não encontrado"));
        return new JobResponse(j);
    }

    @Transactional(rollbackOn = Exception.class)
    public JobResponse create(JobRequest request) {
        UserWorker worker = userWorkerRepository.findById(request.getWorkerId())
                .orElseThrow(() -> new ResourceNotFoundException("Trabalhador não encontrado"));

        Job job = new Job();
        job.setWorker(worker);
        job.setLocation(request.getLocation()); // Usando o campo location diretamente
        job.setDescription(request.getDescription());
        job.setAvaliation(0.0);
        job.setPrice(request.getPrice());
        job.setContracts(new ArrayList<>());

        Job savedJob = jobRepository.save(job);

        return new JobResponse(savedJob);
    }

    @Transactional(rollbackOn = Exception.class)
    public void delete(Long id) {
        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Trabalho com ID " + id + " não encontrado"));

        jobRepository.delete(job);
    }

    @Transactional(rollbackOn = Exception.class)
    public JobResponse update(Long id, JobRequest request) {
        Job existingJob = jobRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Trabalho com ID " + id + " não encontrado"));

        UserWorker worker = userWorkerRepository.findById(request.getWorkerId())
                .orElseThrow(() -> new ResourceNotFoundException("Trabalhador com ID " + request.getWorkerId() + " não encontrado"));

        existingJob.setWorker(worker);
        existingJob.setLocation(request.getLocation());
        existingJob.setDescription(request.getDescription());
        existingJob.setPrice(request.getPrice());

        return new JobResponse(jobRepository.save(existingJob));
    }

       public List<JobResponse> searchByDescription(String description) {
            List<Job> jobs = jobRepository.findByDescriptionContainingIgnoreCase(description);
    
            return jobs.stream()
                    .map(JobResponse::new) 
                    .collect(Collectors.toList());
        }




}
