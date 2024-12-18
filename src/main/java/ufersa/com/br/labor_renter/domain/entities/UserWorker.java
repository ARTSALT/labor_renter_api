package ufersa.com.br.labor_renter.domain.entities;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "user_worker")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserWorker extends Contractor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    private Long id;

    @Column(nullable = false)
    private String documento;

}
