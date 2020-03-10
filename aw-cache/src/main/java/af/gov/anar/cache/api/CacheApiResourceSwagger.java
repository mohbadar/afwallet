
package af.gov.anar.cache.api;

import af.gov.anar.lang.data.EnumOptionData;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
final class CacheApiResourceSwagger {
    private CacheApiResourceSwagger() {

    }

    @ApiModel(value = "GetCachesResponse")
    public static final class GetCachesResponse {
        private GetCachesResponse() {

        }
        public EnumOptionData cacheType;
        public boolean enabled;
    }

    @ApiModel(value = "PutCachesRequest")
    public static final class PutCachesRequest {
        private PutCachesRequest() {

        }
        @ApiModelProperty(example = "2")
        public Long cacheType;

    }

    @ApiModel(value = "PutCachesResponse")
    public static final class PutCachesResponse {
        private PutCachesResponse() {

        }
        public static final class PutCachechangesSwagger{
            private PutCachechangesSwagger() {

            }
            @ApiModelProperty(example = "2")
            public Long cacheType;

        }
        public PutCachechangesSwagger cacheType;

    }
}
