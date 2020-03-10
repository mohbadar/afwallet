
package af.gov.anar.spm.spm.exception;


import af.gov.anar.lang.infrastructure.exception.common.AbstractPlatformResourceNotFoundException;

public class LookupTableNotFoundException extends AbstractPlatformResourceNotFoundException {

    public LookupTableNotFoundException(final String key) {
        super("error.msg.survey.lookuptable.notfound", "Lookup table with id " + key + " not found!", key);
    }
}
