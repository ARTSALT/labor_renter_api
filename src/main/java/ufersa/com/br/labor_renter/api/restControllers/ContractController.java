package ufersa.com.br.labor_renter.api.restControllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ufersa.com.br.labor_renter.api.dto.responses.ContractResponse;
import ufersa.com.br.labor_renter.domain.services.ContractService;

import java.util.List;

@RestController
@RequestMapping("api/v1/contract")
public class ContractController {
    private final ContractService service;

    public ContractController(ContractService service) {
        this.service = service;
    }

    @GetMapping("")
    public ResponseEntity<List<ContractResponse>> findAll() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.FOUND);
    }
}
