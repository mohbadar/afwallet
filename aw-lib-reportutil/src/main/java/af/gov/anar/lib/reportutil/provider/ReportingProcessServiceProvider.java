
package af.gov.anar.lib.reportutil.provider;


import af.gov.anar.lib.reportutil.annotation.ReportService;
import af.gov.anar.lib.reportutil.service.ReportingProcessService;
import org.apache.commons.lang.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Component
public class ReportingProcessServiceProvider implements ApplicationContextAware {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReportingProcessServiceProvider.class);

    private ApplicationContext applicationContext;

    Map<String, String> reportingProcessServices = null;

    ReportingProcessServiceProvider() {
        super();
    }

    public ReportingProcessService findReportingProcessService(final String reportType) {
        if (this.reportingProcessServices.containsKey(reportType)) { return (ReportingProcessService) this.applicationContext
                .getBean(this.reportingProcessServices.get(reportType)); }
        return null;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        this.initializeRegistry();
    }

    public Collection<String> findAllReportingTypes() {
        return this.reportingProcessServices.keySet();

    }

    private void initializeRegistry() {
        if (this.reportingProcessServices == null) {
            this.reportingProcessServices = new HashMap<>();

            final String[] reportServiceBeans = this.applicationContext.getBeanNamesForAnnotation(ReportService.class);
            if (ArrayUtils.isNotEmpty(reportServiceBeans)) {
                for (final String reportName : reportServiceBeans) {
                    LOGGER.info("Register report service '" + reportName + "' ...");
                    final ReportService service = this.applicationContext.findAnnotationOnBean(reportName, ReportService.class);
                    try {
                        this.reportingProcessServices.put(service.type(), reportName);
                    } catch (final Throwable th) {
                        LOGGER.error("Unable to register reporting service '" + reportName + "'!", th);
                    }
                }
            }
        }
    }
}
