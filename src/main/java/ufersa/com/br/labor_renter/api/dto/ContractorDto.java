package ufersa.com.br.labor_renter.api.dto;

import lombok.*;
import ufersa.com.br.labor_renter.domain.entities.Contractor;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContractorDto {
    Long id;
    String cpf;
    String nome;
    String email;
    int contractedPlumbers;
    int contractedPainters;
    int contractedElectricians;

    public ContractorDto(Contractor contractor) {
        this.id = contractor.getId();
        this.cpf = contractor.getCpf();
        this.nome = contractor.getName();
        this.email = contractor.getEmail();
        this.contractedPlumbers = contractor.getContractedPlumbers();
        this.contractedPainters = contractor.getContractedPainters();
        this.contractedElectricians = contractor.getContractedElectricians();
    }
}
