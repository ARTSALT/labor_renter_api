package ufersa.com.br.labor_renter.api.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ufersa.com.br.labor_renter.domain.entities.Contract;
import ufersa.com.br.labor_renter.domain.entities.Job;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JobResponse {
    private Long id;
    private String workerName;
    private Double avaliation;
    private String description;
    private String location;
    private Double price;
    private List<Long> contractIds;

    public JobResponse(Job entity) {
        this.id = entity.getId();
        this.workerName = entity.getWorker().getName();
        this.avaliation = entity.getAvaliation();
        this.description = entity.getDescription();
        this.location = entity.getLocation();
        this.price = entity.getPrice();
        this.contractIds = entity.getContracts().stream().map(Contract::getId).toList();
    }
}
