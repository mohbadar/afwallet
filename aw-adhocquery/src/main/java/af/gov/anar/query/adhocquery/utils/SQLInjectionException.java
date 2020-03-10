
package af.gov.anar.query.adhocquery.utils;


import af.gov.anar.lang.infrastructure.exception.common.PlatformApiDataValidationException;

public class SQLInjectionException extends PlatformApiDataValidationException {

	public SQLInjectionException() {
		super("error.msg.found.sql.injection", "Unexpected SQL Commands found", null);
	}

}
