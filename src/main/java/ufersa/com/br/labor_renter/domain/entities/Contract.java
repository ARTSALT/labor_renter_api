package ufersa.com.br.labor_renter.domain.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "contracts")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(optional = false)
    @JoinColumn(name = "contractor_id", referencedColumnName = "id")
    private Contractor contractor;

    @ManyToOne(optional = false)
    @JoinColumn(name = "worker_id", referencedColumnName = "id")
    private UserWorker worker;

    @ManyToOne(optional = false)
    @JoinColumn(name = "job_id", referencedColumnName = "id")
    private Job job;
}
