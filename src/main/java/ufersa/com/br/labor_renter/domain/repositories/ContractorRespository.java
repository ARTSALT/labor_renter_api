package ufersa.com.br.labor_renter.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ufersa.com.br.labor_renter.domain.entities.Contractor;

public interface ContractorRespository extends JpaRepository<Contractor, Long> {

}
