package af.gov.anar.ebreshna.common.province;

import af.gov.anar.ebreshna.common.province.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProvinceRepository extends JpaRepository<Province, Long> {
}

