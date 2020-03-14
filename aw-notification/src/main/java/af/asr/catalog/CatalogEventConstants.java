package af.asr.catalog;

public interface CatalogEventConstants {

    String DESTINATION = "nun-v1";

    String SELECTOR_NAME = "action";

    String POST_CATALOG = "post-catalog";
    String SELECTOR_POST_CATALOG = SELECTOR_NAME + " = '" + POST_CATALOG + "'";
    String DELETE_CATALOG = "delete-catalog";
    String SELECTOR_DELETE_CATALOG = SELECTOR_NAME + " = '" + DELETE_CATALOG + "'";
    String DELETE_FIELD = "delete-field";
    String SELECTOR_DELETE_FIELD = SELECTOR_NAME + " = '" + DELETE_FIELD + "'";
    String PUT_FIELD = "put-field";
    String SELECTOR_PUT_FIELD = SELECTOR_NAME + " = '" + PUT_FIELD + "'";
}
