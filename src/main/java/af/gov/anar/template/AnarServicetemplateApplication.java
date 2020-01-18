package af.gov.anar.template;

import af.gov.anar.lang.applicationname.EnableApplicationName;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableApplicationName
@ComponentScan(basePackages = {"af.gov.*"})
public class AnarServicetemplateApplication {

	public static void main(String[] args) {
		SpringApplication.run(AnarServicetemplateApplication.class, args);
	}

}
