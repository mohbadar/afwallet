package af.gov.anar.ebreshna.infrastructure.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * it is essential to have proper specifications for the back-end APIs. At the same time, the API documentation should be informative, readable, and easy to follow.
 * Moreover, reference documentation should simultaneously describe every change in the API. Accomplishing this manually is a tedious exercise, so automation of the process was inevitable.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    /**
     * After the Docket bean is defined, its select() method returns an instance of ApiSelectorBuilder, which provides a way to control the endpoints exposed by Swagger.
     *
     * Predicates for selection of RequestHandlers can be configured with the help of RequestHandlerSelectors and PathSelectors. Using any() for both will make documentation for your entire API available through Swagger.
     *
     * Within Swagger’s response is a list of all controllers defined in your application. Clicking on any of them will list the valid HTTP methods (DELETE, GET, HEAD, OPTIONS, PATCH, POST, PUT).
     *
     * Expanding each method provides additional useful data, such as response status, content-type, and a list of parameters. It is also possible to try each method using the UI.
     *
     * Swagger’s ability to be synchronized with your code base is crucial. To demonstrate this, you can add a new controller to your application.
     *
     * @return
     */
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * Advance Configuration
     *
     *
     * @Bean
     * public Docket api() {
     *     return new Docket(DocumentationType.SWAGGER_2)
     *       .select()
     *       .apis(RequestHandlerSelectors.basePackage("af.gov.anar.ebreshna.object.api"))
     *       .paths(PathSelectors.ant("/foos/*"))
     *       .build();
     * }
     * OR
     *
     *
     *
     @Bean
     public Docket postsApi() {
     return new Docket(DocumentationType.SWAGGER_2).groupName("public-api")
     .apiInfo(apiInfo()).select().paths(postPaths()).build();
     }

     private Predicate<String> postPaths() {
     return or(regex("/api/posts.*"), regex("/api/servocetemplate.*"));
     }

     private ApiInfo apiInfo() {
     return new ApiInfoBuilder().title("Anar Template PI")
     .description("API reference for developers")
     .termsOfServiceUrl("http://anar.gov.af")
     .contact("admin@gmail.com").license("MIT")
     .licenseUrl("admin@gmail.com").version("1.0").build();
     }

     */
}