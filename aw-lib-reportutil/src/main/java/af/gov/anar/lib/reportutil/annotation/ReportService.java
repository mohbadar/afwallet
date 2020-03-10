
package af.gov.anar.lib.reportutil.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface ReportService {

    /**
     * @return the type of the report
     */
    String type();
}