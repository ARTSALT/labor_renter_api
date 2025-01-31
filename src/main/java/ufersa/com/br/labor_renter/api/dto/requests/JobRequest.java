package ufersa.com.br.labor_renter.api.dto.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ufersa.com.br.labor_renter.domain.entities.Job;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JobRequest {
    @NotNull(message = "O campo workerId é obrigatório")
    private Long workerId;

    @NotNull(message = "O campo localização é obrigatório")
    private String location;

    @NotBlank(message = "O campo descrição é obrigatório")
    private String description;

    @NotBlank(message = "O campo preço é obrigatório")
    private Double price;

    public JobRequest(Job entity) {
        this.workerId = entity.getWorker().getId();
        this.description = entity.getDescription();
        this.location = entity.getLocation();
        this.price = entity.getPrice();
    }
}
