
package af.asr.office.config;

import af.asr.office.ServiceConstants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan(
    basePackages = {
        "af.*"
    }
)
public class OfficeServiceConfiguration {

  public OfficeServiceConfiguration() {
    super();
  }

  @Bean(name = ServiceConstants.SERVICE_LOGGER_NAME)
  public Logger logger() {
    return LoggerFactory.getLogger(ServiceConstants.SERVICE_LOGGER_NAME);
  }

  @Bean(name = ServiceConstants.JSON_SERIALIZER_NAME)
  public Gson gson() {
    return new GsonBuilder().create();
  }
}
