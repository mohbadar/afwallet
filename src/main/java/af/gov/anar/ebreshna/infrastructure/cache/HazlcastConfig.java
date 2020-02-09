package af.gov.anar.ebreshna.infrastructure.cache;

import af.gov.anar.ebreshna.infrastructure.constant.CacheConstants;
import com.hazelcast.config.Config;
import com.hazelcast.config.EvictionPolicy;
import com.hazelcast.config.MapConfig;
import com.hazelcast.config.MaxSizeConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * By annotating the method using the @Cacheable annotation the result of the subsequent invocations will be cached. The @CacheEvict(allEntries=true) clears all the entries from the cache.
 *
 * @Cachable: Is used to adding the cache behavior to a method. We can also give the name to it, where the cache results would be saved.
 * @CacheEvict: Is used to remove the one or more cached values. allEntries=true parameter allows us to remove all entries from the cache.
 * @CachePut: Is used to update the cached value.
 *
 * Creating an Item model
 *
 */
@Configuration
public class HazlcastConfig {


    @Bean
    public Config hazelCastConfig(){
        return new Config()
                .setInstanceName(CacheConstants.HAZELCAST_INSTANCE_NAME)
                .addMapConfig(
                        new MapConfig()
                                .setName(CacheConstants.HAZELCAST_NAME)
                                .setMaxSizeConfig(new MaxSizeConfig(CacheConstants.HAZELCAST_MAX_SIZE, MaxSizeConfig.MaxSizePolicy.FREE_HEAP_SIZE))
                                .setEvictionPolicy(EvictionPolicy.LRU)
                                .setTimeToLiveSeconds(CacheConstants.HAZELCAST_TIME_TO_LIVE_SECONDS));
    }

}
