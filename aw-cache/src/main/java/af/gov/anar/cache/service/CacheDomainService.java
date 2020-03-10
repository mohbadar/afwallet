
package af.gov.anar.cache.service;

import af.gov.anar.cache.domain.CacheType;
import af.gov.anar.cache.domain.PlatformCache;
import af.gov.anar.cache.domain.PlatformCacheRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CacheDomainService {

    @Autowired
    private PlatformCacheRepository cacheTypeRepository;

    public boolean isEhcacheEnabled() {
        return this.cacheTypeRepository.getOne(Long.valueOf(1)).isEhcacheEnabled();
    }

    @Transactional
    public void updateCache(final CacheType cacheType) {
        final PlatformCache cache = this.cacheTypeRepository.getOne(Long.valueOf(1));
        cache.update(cacheType);
        this.cacheTypeRepository.save(cache);
    }




}