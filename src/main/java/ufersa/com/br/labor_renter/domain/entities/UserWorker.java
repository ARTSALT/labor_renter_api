package ufersa.com.br.labor_renter.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;


@Entity
@Table(name = "user_worker")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserWorker extends Contractor {
    @Column(nullable = false)
    private String documento;
}
