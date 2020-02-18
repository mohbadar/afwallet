package af.gov.anar.ebreshna;

import af.gov.anar.lang.applicationname.EnableApplicationName;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableAsync;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

@SpringBootApplication
@EnableApplicationName
@ComponentScan(basePackages = {"af.*"})
@EnableJpaRepositories(basePackages = {"af.*"})
@EntityScan(basePackages = {"af.*"})
@EnableSwagger2
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableAsync
@EnableRetry
@Slf4j
public class Application {

	public static void main(String[] args) throws InterruptedException, ExecutionException, IOException {
		SpringApplication.run(Application.class, args);
	}

}
