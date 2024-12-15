package ufersa.com.br.labor_renter.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ufersa.com.br.labor_renter.domain.entities.UserWorker;

public interface UserWorkerRepository extends JpaRepository<UserWorker, Long> {

}