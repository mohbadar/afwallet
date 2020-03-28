package af.gov.anar.corona.patient.repository;

import af.gov.anar.corona.patient.model.Nationalty;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NationalityRepository extends CrudRepository<Nationalty, Long>, RevisionRepository<Nationalty, Long, Integer> {
    Iterable<Nationalty>  findByDeleted(boolean deleted);
    Iterable<Nationalty>  findByDeletedBy(String deletedBy);
}
