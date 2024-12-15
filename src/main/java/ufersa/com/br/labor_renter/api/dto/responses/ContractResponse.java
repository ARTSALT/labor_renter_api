package ufersa.com.br.labor_renter.api.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ufersa.com.br.labor_renter.domain.entities.Contract;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContractResponse {
    private Long id;
    private Long contractorId;
    private Long workerId;
    private Long jobId;

    public ContractResponse(Contract entity) {
        this.id = entity.getId();
        this.contractorId = entity.getContractor().getId();
        this.workerId = entity.getWorker().getId();
        this.jobId = entity.getJob().getId();
    }
}
