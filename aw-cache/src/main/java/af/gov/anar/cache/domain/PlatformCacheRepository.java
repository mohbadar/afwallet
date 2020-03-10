
package af.gov.anar.cache.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PlatformCacheRepository extends JpaRepository<PlatformCache, Long>, JpaSpecificationExecutor<PlatformCache> {
    //
}