package ufersa.com.br.labor_renter.api.restControllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import ufersa.com.br.labor_renter.api.dto.ContractorDto;
import ufersa.com.br.labor_renter.domain.services.ContractorService;

import java.util.List;

public class ContractorController {

    private ContractorService service;

    public ResponseEntity<List<ContractorDto>> listAll() {
        List<ContractorDto> response = service.listALl();
        return new ResponseEntity<>(response, HttpStatus.FOUND);
    }
}
