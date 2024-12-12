package ufersa.com.br.labor_renter.domain.services;

import org.springframework.stereotype.Service;
import ufersa.com.br.labor_renter.api.dto.ContractorDto;
import ufersa.com.br.labor_renter.domain.entities.Contractor;
import ufersa.com.br.labor_renter.domain.repositories.ContractorRespository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContractorService {
    private final ContractorRespository contractorRespository;

    public ContractorService(ContractorRespository contractorRespository) {
        this.contractorRespository = contractorRespository;
    }

    public List<ContractorDto> listALl() {
        List<Contractor> response = contractorRespository.findAll();

        return response.stream().map(ContractorDto::new).collect(Collectors.toList());
    }
}
