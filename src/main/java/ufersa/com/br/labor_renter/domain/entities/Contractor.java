package ufersa.com.br.labor_renter.domain.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@Table(name = "contractors")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Contractor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id",nullable = false)
    Long id;

    @Column(name = "cpf",nullable = false)
    String cpf;

    @Column(name = "name",nullable = false)
    String name;

    @Column(name = "email",nullable = false)
    String email;

    @Column(name = "password", nullable = false)
    String password;

    @OneToMany(mappedBy = "contractor", cascade = CascadeType.ALL, orphanRemoval = true,
            targetEntity = Address.class, fetch = FetchType.EAGER)
    List<Address> address;

    @Column(name = "contracted_plumbers",nullable = false)
    int contractedPlumbers;

    @Column(name = "contracted_painters",nullable = false)
    int contractedPainters;

    @Column(name = "contracted_electricians",nullable = false)
    int contractedElectricians;


}
