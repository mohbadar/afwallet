package af.gov.anar.ebreshna.common.base.province;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProvinceRepository extends JpaRepository<Province, Long> {

    Province findByName(String name);
    Province findByProvinceCode(String provinceCode);
}

