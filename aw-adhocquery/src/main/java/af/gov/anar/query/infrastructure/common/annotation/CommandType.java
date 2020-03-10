
package af.gov.anar.query.infrastructure.common.annotation;

import java.lang.annotation.*;

/**
 * Specifies the command type for the annotated class.<br>
 * <br>
 * The entity name (e.g. CLIENT, SAVINGSACCOUNT, LOANPRODUCT) and the action (e.g. CREATE, DELETE) must be given.
 *
 * @author Markus Geiss
 * @version 1.0
 * @since 15.06
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface CommandType {

    /**
     * Returns the name of the entity for this {@link CommandType}.
     */
    String entity();

    /**
     * Return the name of the action for this {@link CommandType}.
     */
    String action();
}
