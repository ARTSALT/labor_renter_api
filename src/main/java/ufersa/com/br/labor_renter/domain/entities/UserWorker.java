package ufersa.com.br.labor_renter.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "user_worker")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserWorker extends Contractor {
    @Column(nullable = false)
    private String documento;

    @OneToMany(mappedBy = "worker", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Job> jobs = new ArrayList<>();

}
