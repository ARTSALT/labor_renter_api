package ufersa.com.br.labor_renter.domain.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "job")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "worker_id", referencedColumnName = "id", nullable = false)
    private UserWorker worker;


    @OneToOne
    @JoinColumn(name = "contractor_id", referencedColumnName = "id", nullable = false)
    private Contractor contractor;

    @Column(nullable = false)
    private Double avaliation;

    @Column(nullable = false)
    private String description;

    @OneToOne
    @JoinColumn(name = "Address_id", referencedColumnName = "id", nullable = false)
    private Address location;

    // private List<Contract> contracts;

    /*public void updateAvaliation(double avaliation) {
        setAvaliation(getAvaliation() + avaliation / getContracts.length() + 1);
    }*/
}
