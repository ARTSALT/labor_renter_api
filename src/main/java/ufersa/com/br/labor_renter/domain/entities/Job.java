package ufersa.com.br.labor_renter.domain.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "job")
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "worker_id", referencedColumnName = "id", nullable = false)
    private UserWorker worker;

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
