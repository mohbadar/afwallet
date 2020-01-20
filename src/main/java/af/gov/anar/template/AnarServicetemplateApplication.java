package af.gov.anar.template;

import af.gov.anar.lang.applicationname.EnableApplicationName;
import af.gov.anar.template.infrastructure.multitenancy.MultitenantConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableApplicationName
@ComponentScan(basePackages = {"af.*"})
@EnableJpaRepositories(basePackages = {"af.*"})
@EntityScan(basePackages = {"af.*"})
@ImportAutoConfiguration(MultitenantConfiguration.class)
public class AnarServicetemplateApplication {

	public static void main(String[] args) {
		SpringApplication.run(AnarServicetemplateApplication.class, args);
	}

}
