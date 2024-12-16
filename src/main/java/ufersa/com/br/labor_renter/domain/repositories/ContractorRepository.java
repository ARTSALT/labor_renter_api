package ufersa.com.br.labor_renter.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ufersa.com.br.labor_renter.domain.entities.Contractor;

import java.util.Optional;

public interface ContractorRepository extends JpaRepository<Contractor, Long> {
    Optional<Contractor> findByEmail(String email);
    boolean existsByEmail(String email);

    Optional<Contractor> findByCpf(String cpf);
    boolean existsByCpf(String cpf);

    Optional<Contractor> findByPassword(String password);
    boolean existsByPassword(String password);
}
