package af.gov.anar.corona.patient.repository;

import af.gov.anar.corona.patient.model.District;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DistrictRepository extends CrudRepository<District, Long>, RevisionRepository<District, Long, Integer> {

    Iterable<District>  findByDeleted(boolean deleted);
    Iterable<District>  findByDeletedBy(String deletedBy);
}
