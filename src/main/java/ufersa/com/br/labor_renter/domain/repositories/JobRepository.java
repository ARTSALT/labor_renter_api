package ufersa.com.br.labor_renter.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ufersa.com.br.labor_renter.domain.entities.Job;

import java.util.List;

public interface JobRepository extends JpaRepository<Job, Long> {
  List<Job> findByDescriptionContainingIgnoreCase(String description);
}
