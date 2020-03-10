
package af.gov.anar.query.adhocquery.api;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import af.gov.anar.query.adhocquery.data.AdHocData;
import af.gov.anar.query.adhocquery.service.AdHocReadPlatformService;
import af.gov.anar.query.infrastructure.common.api.ApiRequestParameterHelper;
import af.gov.anar.query.infrastructure.common.serialization.ApiRequestJsonSerializationSettings;
import af.gov.anar.query.infrastructure.common.serialization.DefaultToApiJsonSerializer;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Path("/adhocquery")
@Component
@Scope("singleton")
public class AdHocApiResource {

    /**
     * The set of parameters that are supported in response for {@link AdHocData}
     */ 
    private final Set<String> RESPONSE_DATA_PARAMETERS = new HashSet<>(Arrays.asList("id", "name", "query", "tableName","tableField","isActive","createdBy","createdOn","createdById","updatedById","updatedOn","email"));
    
    private final AdHocReadPlatformService adHocReadPlatformService;
    private final DefaultToApiJsonSerializer<AdHocData> toApiJsonSerializer;
    private final ApiRequestParameterHelper apiRequestParameterHelper;
//    private final PortfolioCommandSourceWritePlatformService commandsSourceWritePlatformService;

    @Autowired
    public AdHocApiResource(final AdHocReadPlatformService readPlatformService,
            final DefaultToApiJsonSerializer<AdHocData> toApiJsonSerializer,
            final ApiRequestParameterHelper apiRequestParameterHelper
//            ,final PortfolioCommandSourceWritePlatformService commandsSourceWritePlatformService
    ) {
        this.adHocReadPlatformService = readPlatformService;
        this.toApiJsonSerializer = toApiJsonSerializer;
        this.apiRequestParameterHelper = apiRequestParameterHelper;
//        this.commandsSourceWritePlatformService = commandsSourceWritePlatformService;
    }

    @GET
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    public String retrieveAll(@Context final UriInfo uriInfo) {
    	
        
        final Collection<AdHocData> adhocs = this.adHocReadPlatformService.retrieveAllAdHocQuery();
        final ApiRequestJsonSerializationSettings settings = this.apiRequestParameterHelper.process(uriInfo.getQueryParameters());
        return this.toApiJsonSerializer.serialize(settings, adhocs, this.RESPONSE_DATA_PARAMETERS);
    }
    @GET
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    @Path("template")
    public String template(@Context final UriInfo uriInfo) {
        
        final AdHocData user = this.adHocReadPlatformService.retrieveNewAdHocDetails();
        final ApiRequestJsonSerializationSettings settings = this.apiRequestParameterHelper.process(uriInfo.getQueryParameters());
        return this.toApiJsonSerializer.serialize(settings, user, this.RESPONSE_DATA_PARAMETERS);
    }
    @POST
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    public String createAdHocQuery(final String apiRequestBodyAsJson) {

//        final CommandWrapper commandRequest = new CommandWrapperBuilder() //
//                .createAdHoc() //
//                .withJson(apiRequestBodyAsJson) //
//                .build();

//        final CommandProcessingResult result = this.commandsSourceWritePlatformService.logCommandSource(commandRequest);
//
//        return this.toApiJsonSerializer.serialize(result);
        return null;
    }

    @GET
    @Path("{adHocId}")
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    public String retrieveAdHocQuery(@PathParam("adHocId") final Long adHocId, @Context final UriInfo uriInfo) {

        

        final ApiRequestJsonSerializationSettings settings = this.apiRequestParameterHelper.process(uriInfo.getQueryParameters());

        final AdHocData adhoc = this.adHocReadPlatformService.retrieveOne(adHocId);

        return this.toApiJsonSerializer.serialize(settings, adhoc, this.RESPONSE_DATA_PARAMETERS);
    }
    @PUT
    @Path("{adHocId}")
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    public String update(@PathParam("adHocId") final Long adHocId, final String apiRequestBodyAsJson) {

//        final CommandWrapper commandRequest = new CommandWrapperBuilder() //
//                .updateAdHoc(adHocId) //
//                .withJson(apiRequestBodyAsJson) //
//                .build();
//
//        final CommandProcessingResult result = this.commandsSourceWritePlatformService.logCommandSource(commandRequest);
//
//        return this.toApiJsonSerializer.serialize(result);
        return null;
    }
     /**
     * Delete AdHocQuery
     * 
     * @param adHocId
     * @return
     */
    @DELETE
    @Path("{adHocId}")
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    public String deleteAdHocQuery(@PathParam("adHocId") final Long adHocId) {

//        final CommandWrapper commandRequest = new CommandWrapperBuilder() //
//                .deleteAdHoc(adHocId) //
//                .build();
//
//        final CommandProcessingResult result = this.commandsSourceWritePlatformService.logCommandSource(commandRequest);
//
//        return this.toApiJsonSerializer.serialize(result);
        return null;
    }

    private boolean is(final String commandParam, final String commandValue) {
        return StringUtils.isNotBlank(commandParam) && commandParam.trim().equalsIgnoreCase(commandValue);
    }

}