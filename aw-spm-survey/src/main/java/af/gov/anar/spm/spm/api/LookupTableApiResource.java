
package af.gov.anar.spm.spm.api;

import af.gov.anar.spm.spm.data.LookupTableData;
import af.gov.anar.spm.spm.domain.LookupTable;
import af.gov.anar.spm.spm.domain.Survey;
import af.gov.anar.spm.spm.exception.LookupTableNotFoundException;
import af.gov.anar.spm.spm.exception.SurveyNotFoundException;
import af.gov.anar.spm.spm.service.LookupTableService;
import af.gov.anar.spm.spm.service.SpmService;
import af.gov.anar.spm.spm.util.LookupTableMapper;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/surveys/{surveyId}/lookuptables")
@Api(value = "SPM API - LookUp Table", description = "The SPM API provides the ability to create custom surveys to collect social performance measurentment data or any additional questionnaire a institution want to collect.")
public class LookupTableApiResource {

    private final SpmService spmService;
    private final LookupTableService lookupTableService;

    @Autowired
    public LookupTableApiResource(final SpmService spmService,
                                  final LookupTableService lookupTableService) {
        super();
        this.spmService = spmService;
        this.lookupTableService = lookupTableService;
    }

    @GetMapping(value = "")
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    @Transactional
    @ApiOperation(value = "List all Lookup Table entries", notes = "List all Lookup Table entries for a survey.")
    @ApiResponses({@ApiResponse(code = 200, message = "", response = LookupTableData.class, responseContainer = "list")})
    public List<LookupTableData> fetchLookupTables(@PathParam("surveyId") @ApiParam(value = "Enter surveyId") final Long surveyId) {

        final Survey survey = findSurvey(surveyId);

        final List<LookupTable> lookupTables = this.lookupTableService.findBySurvey(survey);

        if (lookupTables != null) {
            return LookupTableMapper.map(lookupTables);
        }

        return Collections.EMPTY_LIST;
    }

    @GetMapping(value = "/{key}")
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    @Transactional
    @ApiOperation(value = "Retrieve a Lookup Table entry", notes = "Retrieve a Lookup Table entry for a survey.")
    @ApiResponses({@ApiResponse(code = 200, message = "", response = LookupTableData.class)})
    public LookupTableData findLookupTable(@PathParam("surveyId") @ApiParam(value = "Enter surveyId") final Long surveyId,
                                           @PathParam("key") @ApiParam(value = "Enter key") final String key) {

        findSurvey(surveyId);

        final List<LookupTable> lookupTables = this.lookupTableService.findByKey(key);

        if (lookupTables == null || lookupTables.isEmpty()) {
            throw new LookupTableNotFoundException(key);
        }

        return LookupTableMapper.map(lookupTables).get(0);
    }

    @PostMapping(value = "")
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    @Transactional
    @ApiOperation(value = "Create a Lookup Table entry", notes = "Add a new entry to a survey.\n" + "\n" + "Mandatory Fields\n" + "key, score, validFrom, validTo")
    @ApiResponses({@ApiResponse(code = 200, message = "OK")})
    public void createLookupTable(@PathParam("surveyId") @ApiParam(value = "Enter surveyId") final Long surveyId,
                                  final LookupTableData lookupTableData) {

        final Survey survey = findSurvey(surveyId);

        this.lookupTableService.createLookupTable(LookupTableMapper.map(lookupTableData, survey));
    }

    private Survey findSurvey(final Long surveyId) {
        final Survey survey = this.spmService.findById(surveyId);
        if (survey == null) {
            throw new SurveyNotFoundException(surveyId);
        }
        return survey;
    }
}
