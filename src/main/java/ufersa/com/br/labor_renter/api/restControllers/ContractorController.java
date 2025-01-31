package ufersa.com.br.labor_renter.api.restControllers;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ufersa.com.br.labor_renter.api.dto.requests.ContractorCreateRequest;
import ufersa.com.br.labor_renter.api.dto.requests.ContractorRequest;
import ufersa.com.br.labor_renter.api.dto.responses.ContractorResponse;
import ufersa.com.br.labor_renter.domain.services.ContractorService;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("api/v1/contractor")
public class ContractorController {
    private final ContractorService service;

    public ContractorController(ContractorService service) {
        this.service = service;
    }

    @GetMapping("")
    public ResponseEntity<List<ContractorResponse>> listAll() {
        return new ResponseEntity<>(service.listALl(), HttpStatus.FOUND);
    }

    @PostMapping("")
    public ResponseEntity<ContractorResponse> create(@Valid @RequestBody ContractorCreateRequest request) {
        return new ResponseEntity<>(service.create(request), HttpStatus.CREATED);
    }

    @GetMapping("/{contractor_id}")
    public ResponseEntity<ContractorResponse> get(@PathVariable long contractor_id) {
        return new ResponseEntity<>(service.get(contractor_id), HttpStatus.FOUND);
    }

    @DeleteMapping("/{contractor_id}")
    public ResponseEntity<Void> delete(@PathVariable long contractor_id) {
        service.delete(contractor_id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/{contractor_id}")
    public ResponseEntity<ContractorResponse> update(@Valid @RequestBody ContractorRequest entity, @PathVariable long contractor_id) {
        return new ResponseEntity<>(service.update(entity, contractor_id), HttpStatus.OK);
    }
}
