
package af.gov.anar.cache.service;

import java.util.Map;

import af.gov.anar.cache.domain.CacheType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CacheWritePlatformServiceJpaRepositoryImpl implements CacheWritePlatformService {

    private final CacheDomainService cacheDomainService;
    private final RuntimeDelegatingCacheManager cacheService;

    @Autowired
    public CacheWritePlatformServiceJpaRepositoryImpl(final CacheDomainService cacheDomainService,
            @Qualifier("runtimeDelegatingCacheManager") final RuntimeDelegatingCacheManager cacheService) {
        this.cacheDomainService =cacheDomainService;
        this.cacheService = cacheService;
    }

    @Transactional
    @Override
    public Map<String, Object> switchToCache(final CacheType toCacheType) {

        final boolean ehCacheEnabled = this.cacheDomainService.isEhcacheEnabled();

        final Map<String, Object> changes = this.cacheService.switchToCache(ehCacheEnabled, toCacheType);

        if (!changes.isEmpty()) {
            this.cacheDomainService.updateCache(toCacheType);
        }

        return changes;
    }
}