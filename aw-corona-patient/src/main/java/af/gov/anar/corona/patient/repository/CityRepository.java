package af.gov.anar.corona.patient.repository;

import af.gov.anar.corona.patient.model.City;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends CrudRepository<City, Long>, RevisionRepository<City, Long, Integer> {

    Iterable<City>  findByDeleted(boolean deleted);
    Iterable<City>  findByDeletedBy(String deletedBy);
}
