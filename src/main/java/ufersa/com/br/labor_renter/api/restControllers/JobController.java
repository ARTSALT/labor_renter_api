package ufersa.com.br.labor_renter.api.restControllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ufersa.com.br.labor_renter.api.dto.requests.JobRequest;
import ufersa.com.br.labor_renter.api.dto.responses.JobResponse;
import ufersa.com.br.labor_renter.domain.services.JobService;

import java.util.List;

@RestController
@RequestMapping("api/v1/job")
public class JobController {
    private final JobService service;

    public JobController(JobService service) {
        this.service = service;
    }

    @GetMapping("")
    public ResponseEntity<List<JobResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobResponse> findById(@PathVariable Long id) {
        try {
            JobResponse response = service.findById(id);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("")
    public ResponseEntity<JobResponse> create(@RequestBody JobRequest request) {
        try {
            JobResponse response = service.create(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<JobResponse> update(@PathVariable Long id, @RequestBody JobRequest request) {
        try {
            JobResponse response = service.update(id, request);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            service.delete(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<JobResponse>> searchByDescription(@RequestParam String q) {
        List<JobResponse> results = service.searchByDescription(q);
        return ResponseEntity.ok(results);
    }
}
