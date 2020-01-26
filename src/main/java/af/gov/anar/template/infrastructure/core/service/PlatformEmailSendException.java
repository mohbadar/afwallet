
package af.gov.anar.template.infrastructure.core.service;

public class PlatformEmailSendException extends RuntimeException {

    public PlatformEmailSendException(final Throwable e) {
        super(e);
    }
}
