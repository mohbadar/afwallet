package af.asr.customer.catalog.config;

import af.gov.anar.lang.applicationname.EnableApplicationName;
import af.gov.anar.lang.infrastructure.exception.service.EnableServiceException;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration
@EnableAutoConfiguration
@EnableDiscoveryClient
@EnableAsync
@EnableServiceException
@EnableApplicationName
@ComponentScan({
        "af.asr.customer.catalog.api.*"
})
//@Import({
//        CatalogServiceConfiguration.class
//})
public class CatalogRestConfiguration {

    public CatalogRestConfiguration() {
        super();
    }
}
