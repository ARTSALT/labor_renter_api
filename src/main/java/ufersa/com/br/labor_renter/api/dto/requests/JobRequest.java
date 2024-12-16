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
    private Long locationId;

    @NotBlank(message = "A descrição é obrigatória")
    private String description;

    public JobRequest(Job entity) {
        this.workerId = entity.getWorker().getId();
        this.locationId = entity.getLocation().getId();
        this.description = entity.getDescription();
    }
}
