package ufersa.com.br.labor_renter.api.dto.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ufersa.com.br.labor_renter.domain.entities.Contract;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContractCreateRequest {

    @NotBlank(message = "O campo é obrigatório")
    private Long userWorkerId;

    @NotBlank(message = "O campo trabalho é obrigatório")
    private Long jobId;

    public ContractCreateRequest(Contract entity) {
        this.userWorkerId = entity.getWorker().getId();
        this.jobId = entity.getJob().getId();
    }
}
