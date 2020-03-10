
package af.gov.anar.spm.spm.api;

import af.gov.anar.lang.infrastructure.exception.common.UnrecognizedQueryParamException;
import af.gov.anar.spm.spm.data.SurveyData;
import af.gov.anar.spm.spm.domain.Survey;
import af.gov.anar.spm.spm.service.SpmService;
import af.gov.anar.spm.spm.util.SurveyMapper;
import io.swagger.annotations.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/surveys")
@Api(value = "SPM - Serveys", description = "")
public class SpmApiResource {

    private final SpmService spmService;

    @Autowired
    public SpmApiResource( final SpmService spmService) {
        this.spmService = spmService;
    }

    @GetMapping
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    @Transactional
    @ApiOperation(value = "List all Surveys", notes = "")
    @ApiResponses({@ApiResponse(code = 200, message = "", response = SurveyData.class, responseContainer = "list")})
    public List<SurveyData> fetchAllSurveys(@QueryParam("isActive") final Boolean isActive) {
        final List<SurveyData> result = new ArrayList<>();
        List<Survey> surveys = null;
        if(isActive != null && isActive){
            surveys = this.spmService.fetchValidSurveys();
        }else{
            surveys = this.spmService.fetchAllSurveys();
        }
        if (surveys != null) {
            for (final Survey survey : surveys) {
                result.add(SurveyMapper.map(survey));
            }
        }
        return result;
    }

    @GetMapping(value = "/{id}")
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    @Transactional
    @ApiOperation(value = "Retrieve a Survey", notes = "")
    @ApiResponses({@ApiResponse(code = 200, message = "", response = SurveyData.class)})
    public SurveyData findSurvey(@PathParam("id") @ApiParam(value = "Enter id") final Long id) {
        final Survey survey = this.spmService.findById(id);
        return SurveyMapper.map(survey);
    }

    @PostMapping(value = "")
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    @Transactional
    @ApiOperation(value = "Create a Survey", notes = "Adds a new survey to collect client related data.\n" + "\n" + "Mandatory Fields\n" + "\n" + "countryCode, key, name, questions, responses, sequenceNo, text, value")
    @ApiResponses({@ApiResponse(code = 200, message = "OK")})
    public String createSurvey(@ApiParam(value = "Create survey") final SurveyData surveyData) {
        final Survey survey = SurveyMapper.map(surveyData, new Survey());
        this.spmService.createSurvey(survey);
        return getResponse(survey.getId());

    }

    @PutMapping(value = "/{id}")
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    @Transactional
    public String editSurvey(@PathParam("id") final Long id, final SurveyData surveyData) {
        final Survey surveyToUpdate = this.spmService.findById(id);
        final Survey survey = SurveyMapper.map(surveyData, surveyToUpdate);
        this.spmService.updateSurvey(survey);
        return getResponse(survey.getId());
    }

    @PostMapping("/{id}")
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    @Transactional
    @ApiOperation(value = "Deactivate Survey", notes = "")
    @ApiResponses({@ApiResponse(code = 200, message = "OK")})
    public void activateOrDeactivateSurvey(@PathParam("id") final Long id, @QueryParam("command") final String command) {
        if(command != null && command.equalsIgnoreCase("activate")){
            this.spmService.activateSurvey(id);;
        }else if(command != null && command.equalsIgnoreCase("deactivate")){
            this.spmService.deactivateSurvey(id);
        }else{
            throw new UnrecognizedQueryParamException("command", command);
        }
        
    }
    
    private String getResponse(Long id) {
        Gson gson = new Gson();
        HashMap<String, Object> response = new HashMap<>();
        response.put("resourceId", id);
        return gson.toJson(response);
    }
}
