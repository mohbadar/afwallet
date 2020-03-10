
package af.gov.anar.cache.util;

import af.gov.anar.cache.service.RuntimeDelegatingCacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.cache.interceptor.CacheResolver;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class PlatformCacheConfiguration implements CachingConfigurer {

    @Autowired
    private RuntimeDelegatingCacheManager delegatingCacheManager;

    @Bean
    @Override
    public CacheManager cacheManager() {
        return this.delegatingCacheManager;
    }

    @Override
    public CacheResolver cacheResolver() {
        //TODO https://issues.apache.org/jira/browse/FINERACT-705
        return null;
    }

    @Override
    public KeyGenerator keyGenerator() {
        return new DefaultKeyGenerator();
    }

    @Override
    public CacheErrorHandler errorHandler() {
        //TODO https://issues.apache.org/jira/browse/FINERACT-705
        return null;
    }
}