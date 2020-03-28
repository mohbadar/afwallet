package af.gov.anar.corona.patient.repository;

import af.gov.anar.corona.patient.model.Country;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CountryRepository extends CrudRepository<Country, Long>, RevisionRepository<Country, Long, Integer> {

    Optional<Country> findByCountryCode(String countryCode);

    Iterable<Country>  findByDeleted(boolean deleted);
    Iterable<Country>  findByDeletedBy(String deletedBy);

}
