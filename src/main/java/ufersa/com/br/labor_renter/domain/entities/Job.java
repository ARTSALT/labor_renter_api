package ufersa.com.br.labor_renter.domain.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_job")
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private UserWorker worker;

    @Column(nullable = false)
    private Double avaliation;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Address location;

    // private List<Contract> contracts;

    /*public void updateAvaliation(double avaliation) {
        setAvaliation(getAvaliation() + avaliation / getContracts.length() + 1);
    }*/

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserWorker getWorker() {
        return worker;
    }

    public void setWorker(UserWorker worker) {
        this.worker = worker;
    }

    public Double getAvaliation() {
        return avaliation;
    }

    public void setAvaliation(Double avaliation) {
        this.avaliation = avaliation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Address getLocation() {
        return location;
    }

    public void setLocation(Address location) {
        this.location = location;
    }
}
