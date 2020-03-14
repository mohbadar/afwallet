
package af.gov.anar.dck.infrastructure.util;


public interface ParamConstant {

    //****************************MutltiThreading Config Params ********************//
    public static final Integer ADAPTER_CORE_POOLING_SIZE = 2;
    public static final Integer ADAPTER_MAX_POOLING_SIZE = 2;
    public static final Integer ADAPTER_QUEUE_CAPACITY = 1000;
    public static final String ADAPTER_DEFAULT_PREFIX = "ASMIS-THREAD-SERVICE -> ";

    public static final String[] WEBSCOKET_ENDPOINT = new String[] { "/socket"};
    public static final String[] WEBSOCKET_ALLOWED_ORIGINS = new String[] {"*"};
    public static final String[] NOTITICATIONS_DESTINATION_PREFIXES =new String[] { "/notifications"};

}
