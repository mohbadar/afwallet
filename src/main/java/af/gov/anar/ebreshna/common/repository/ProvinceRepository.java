package af.gov.anar.ebreshna.common.repository;

import af.gov.anar.ebreshna.common.model.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProvinceRepository extends JpaRepository<Province, Long> {
}

