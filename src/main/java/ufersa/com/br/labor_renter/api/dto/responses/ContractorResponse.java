package ufersa.com.br.labor_renter.api.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ufersa.com.br.labor_renter.domain.entities.Contractor;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContractorResponse {
    Long id;
    String name;
    String email;

    public ContractorResponse(Contractor entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.email = entity.getEmail();
    }
}
