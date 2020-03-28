package af.asr.corona.infrastructure.config;

import af.asr.corona.infrastructure.constant.ApplicationGenericConstants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
public class MultiThreadConfig {

    @Bean
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(ApplicationGenericConstants.CORE_POOLING_SIZE);
        executor.setMaxPoolSize(ApplicationGenericConstants.MAX_POOLING_SIZE);
        executor.setQueueCapacity(ApplicationGenericConstants.QUEUE_CAPACITY);
        executor.setThreadNamePrefix(ApplicationGenericConstants.DEFAULT_PREFIX);
        executor.initialize();
        return executor;
    }
}
