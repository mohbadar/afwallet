package af.asr;

import af.gov.anar.lang.applicationname.EnableApplicationName;
import feign.form.spring.SpringFormEncoder;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableAsync;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableApplicationName
@ComponentScan(basePackages = {"af.*", "af.asr.*"})
@EnableJpaRepositories(basePackages = {"af.*"})
@EntityScan(basePackages = {"af.*"})
@EnableSwagger2
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableAsync
@EnableRetry
@Slf4j
@EnableFeignClients
public class NotificationApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotificationApplication.class, args);
	}

//	@Bean
//	public GsonDecoder gsonDecoder()
//	{
//		return new GsonDecoder();
//	}
//
//	@Bean
//	public GsonEncoder gsonEncoder(){
//		return new GsonEncoder(springFormEncoder());
//	}
//
//	@Bean
//	public SpringFormEncoder springFormEncoder()
//	{
//		return new SpringFormEncoder();
//	}
}
