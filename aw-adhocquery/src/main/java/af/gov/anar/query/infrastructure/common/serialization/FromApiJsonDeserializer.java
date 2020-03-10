
package af.gov.anar.query.infrastructure.common.serialization;

/**
 * 
 */
public interface FromApiJsonDeserializer<T> {

    T commandFromApiJson(final String json);
}