package af.asr.customer.config;

import af.asr.customer.util.ServiceConstants;
import af.asr.customer.util.UploadProperties;
import af.gov.anar.lang.applicationname.ApplicationName;
import af.gov.anar.lang.applicationname.EnableApplicationName;
import af.gov.anar.lang.infrastructure.exception.service.EnableServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableAutoConfiguration
@EnableDiscoveryClient
@EnableAsync
@EnableServiceException
@EnableApplicationName
@ComponentScan({
        "af.*"
})
//@Import({
//        CatalogRestConfiguration.class,
//        CustomerServiceConfiguration.class
//})
@EnableConfigurationProperties({UploadProperties.class})
public class CustomerRestConfiguration extends WebMvcConfigurerAdapter {

    public CustomerRestConfiguration() {
        super();
    }

    @Bean(name = ServiceConstants.LOGGER_NAME)
    public Logger logger(final ApplicationName applicationName) {
        return LoggerFactory.getLogger(applicationName.getServiceName());
    }

    @Override
    public void configurePathMatch(final PathMatchConfigurer configurer) {
        configurer.setUseSuffixPatternMatch(Boolean.FALSE);
    }
}