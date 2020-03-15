
package af.asr.office.config;

import af.asr.office.ServiceConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableAutoConfiguration
@EnableDiscoveryClient
@EnableAsync
@ComponentScan({
    "af.*"
})
@Import({
    OfficeServiceConfiguration.class
})
public class OfficeRestConfiguration extends WebMvcConfigurerAdapter {

  public OfficeRestConfiguration() {
    super();
  }

  @Bean(name = ServiceConstants.REST_LOGGER_NAME)
  public Logger logger() {
    return LoggerFactory.getLogger(ServiceConstants.REST_LOGGER_NAME);
  }

  @Override
  public void configurePathMatch(final PathMatchConfigurer configurer) {
    configurer.setUseSuffixPatternMatch(Boolean.FALSE);
  }
}
