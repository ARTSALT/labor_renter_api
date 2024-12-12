package ufersa.com.br.labor_renter.domain.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_contracts")
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "contractor_id", referencedColumnName = "id")
    private Contractor contractor;

    @ManyToOne(optional = false)
    @JoinColumn(name = "worker_id", referencedColumnName = "id")
    private UserWorker worker;

    @ManyToOne(optional = false)
    @JoinColumn(name = "job_id", referencedColumnName = "id")
    private Job job;
}
