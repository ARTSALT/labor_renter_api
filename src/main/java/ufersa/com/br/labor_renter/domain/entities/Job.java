package ufersa.com.br.labor_renter.domain.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "job")
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "worker_id", referencedColumnName = "id", nullable = false)
    private UserWorker worker;

    @Column(nullable = false)
    private Double avaliation = 0.0;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private Double price;

    @OneToMany(mappedBy = "job", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Contract> contracts;

    /*public void updateAvaliation(double avaliation) {
        setAvaliation(getAvaliation() + avaliation / getContracts.length() + 1);
    }*/
}
