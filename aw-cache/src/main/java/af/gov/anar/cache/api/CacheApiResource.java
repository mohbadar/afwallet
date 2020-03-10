
package af.gov.anar.cache.api;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import af.gov.anar.cache.data.CacheData;
import af.gov.anar.cache.service.RuntimeDelegatingCacheManager;
import io.swagger.annotations.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/caches")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON })
@Component
@Api(value = "Cache", description = "The following settings are possible for cache:\n" + "\n" + "No Caching: caching turned off\n" + "Single node: caching on for single instance deployments of platorm (works for multiple tenants but only one tomcat)\n" + "By default caching is set to No Caching. Switching between caches results in the cache been clear e.g. from Single node to No cache and back again would clear down the single node cache.")
public class CacheApiResource {

    private final Set<String> RESPONSE_DATA_PARAMETERS = new HashSet<>(Arrays.asList("id"));
    private final String resourceNameForPermissions = "CACHE";

    private final RuntimeDelegatingCacheManager cacheService;

    @Autowired
    public CacheApiResource(    @Qualifier("runtimeDelegatingCacheManager") final RuntimeDelegatingCacheManager cacheService) {
        this.cacheService = cacheService;
    }

    @GET
    @ApiOperation(value = "Retrieve Cache Types", notes = "Returns the list of caches.\n" + "\n" + "Example Requests:\n" + "\n" + "caches")
    @ApiResponses({@ApiResponse(code = 200, message = "", response = CacheApiResourceSwagger.GetCachesResponse.class, responseContainer = "list")})
    public String retrieveAll(@Context final UriInfo uriInfo) {


        final Collection<CacheData> codes = this.cacheService.retrieveAll();
        return codes.toString();
    }

    @PUT
    @ApiOperation(value = "Switch Cache", notes = "Switches the cache to chosen one.")
    @ApiImplicitParams({@ApiImplicitParam(value = "body", required = true, paramType = "body", dataType = "body", format = "body", dataTypeClass = CacheApiResourceSwagger.PutCachesRequest.class )})
    @ApiResponses({@ApiResponse(code = 200, message = "", response = CacheApiResourceSwagger.PutCachesResponse.class)})
    public String switchCache(@ApiParam(hidden = true) final String apiRequestBodyAsJson) {

//        final CommandWrapper commandRequest = new CommandWrapperBuilder().updateCache().withJson(apiRequestBodyAsJson).build();
//
//        final CommandProcessingResult result = this.commandsSourceWritePlatformService.logCommandSource(commandRequest);
//
//        return this.toApiJsonSerializer.serialize(result);
        return null;
    }
}