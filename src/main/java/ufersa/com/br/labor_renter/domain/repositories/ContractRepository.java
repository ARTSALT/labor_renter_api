package ufersa.com.br.labor_renter.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ufersa.com.br.labor_renter.domain.entities.Contract;

public interface ContractRepository extends JpaRepository<Contract, Long> {
}
