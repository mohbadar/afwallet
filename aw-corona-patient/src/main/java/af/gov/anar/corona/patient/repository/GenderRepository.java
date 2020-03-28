package af.gov.anar.corona.patient.repository;

import af.gov.anar.corona.patient.model.Gender;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenderRepository extends CrudRepository<Gender, Long>, RevisionRepository<Gender, Long, Integer> {
}
