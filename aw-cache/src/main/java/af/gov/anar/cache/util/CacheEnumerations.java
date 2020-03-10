
package af.gov.anar.cache.util;


import af.gov.anar.cache.domain.CacheType;
import af.gov.anar.lang.data.EnumOptionData;

public class CacheEnumerations {

    public static EnumOptionData cacheType(final int id) {
        return cacheType(CacheType.fromInt(id));
    }

    public static EnumOptionData cacheType(final CacheType cacheType) {
        EnumOptionData optionData = new EnumOptionData(CacheType.INVALID.getValue().longValue(), CacheType.INVALID.getCode(), "Invalid");
        switch (cacheType) {
            case INVALID:
                optionData = new EnumOptionData(CacheType.INVALID.getValue().longValue(), CacheType.INVALID.getCode(), "Invalid");
            break;
            case NO_CACHE:
                optionData = new EnumOptionData(CacheType.NO_CACHE.getValue().longValue(), CacheType.NO_CACHE.getCode(), "No cache");
            break;
            case SINGLE_NODE:
                optionData = new EnumOptionData(CacheType.SINGLE_NODE.getValue().longValue(), CacheType.SINGLE_NODE.getCode(),
                        "Single node");
            break;
            case MULTI_NODE:
                optionData = new EnumOptionData(CacheType.MULTI_NODE.getValue().longValue(), CacheType.MULTI_NODE.getCode(), "Multi node");
            break;
        }

        return optionData;
    }
}
