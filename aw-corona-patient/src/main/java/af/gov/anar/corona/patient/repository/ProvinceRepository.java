package af.gov.anar.corona.patient.repository;

import af.gov.anar.corona.patient.model.Province;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProvinceRepository extends CrudRepository<Province, Long> , RevisionRepository<Province, Long, Integer> {

    Province findByProvinceName(String name);
    Province findByProvinceCode(String provinceCode);
    Iterable<Province>  findByDeleted(boolean deleted);
    Iterable<Province>  findByDeletedBy(String deletedBy);
}

