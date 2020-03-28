package af.gov.anar.corona.patient.repository;

import af.gov.anar.corona.patient.model.Nationality;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NationalityRepository extends CrudRepository<Nationality, Long>, RevisionRepository<Nationality, Long, Integer> {
    Iterable<Nationality>  findByDeleted(boolean deleted);
    Iterable<Nationality>  findByDeletedBy(String deletedBy);
}
