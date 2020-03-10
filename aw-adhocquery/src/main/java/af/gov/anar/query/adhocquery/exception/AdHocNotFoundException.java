
package af.gov.anar.query.adhocquery.exception;


import af.gov.anar.lang.infrastructure.exception.common.AbstractPlatformResourceNotFoundException;

/**
 * A {@link RuntimeException} thrown when AdHoc  resources are not
 * found.
 */
public class AdHocNotFoundException extends AbstractPlatformResourceNotFoundException {

    public AdHocNotFoundException(final Long id) {
        super("error.msg.adhocquery.adhoc.id.invalid", "Adhoc Record with identifier " + id + " does not exist", id);
    }
}