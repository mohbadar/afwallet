
package af.gov.anar.cache.service;

import af.gov.anar.cache.domain.CacheType;

import java.util.Map;


public interface CacheWritePlatformService {

    Map<String, Object> switchToCache(CacheType cacheType);
}