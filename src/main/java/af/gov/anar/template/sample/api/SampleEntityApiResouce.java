package af.gov.anar.template.sample.api;

import af.gov.anar.api.annotation.ThrowsException;
import af.gov.anar.api.annotation.ThrowsExceptions;
import af.gov.anar.api.handler.ResponseHandler;
import af.gov.anar.api.handler.exception.InternalServerProblemException;
import af.gov.anar.api.handler.exception.ResourceNotFoundException;
import af.gov.anar.template.infrastructure.exception.UnAuthorizedException;
import af.gov.anar.template.sample.model.SampleEntity;
import af.gov.anar.template.sample.service.SampleEntityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javassist.tools.rmi.Sample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/sampleentity")
@Api(value="sampleapi", description="Sample Api for Service Template")
public class SampleEntityApiResouce extends ResponseHandler {


    @Autowired
    private SampleEntityService sampleEntityService;

    @ApiOperation(value = "test endpoint for producing a Map<String, String>", response = Object.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @ThrowsExceptions(value = {
            @ThrowsException(status = HttpStatus.NOT_FOUND, exception= ResourceNotFoundException.class),
            @ThrowsException(status = HttpStatus.UNAUTHORIZED, exception = UnAuthorizedException.class),
            @ThrowsException(status = HttpStatus.INTERNAL_SERVER_ERROR, exception = InternalServerProblemException.class)
    })
    @GetMapping(value = "test", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String,String> test()
    {
        Map<String, String> data = new HashMap<>();
        data.put("data", "test");

        System.out.println("test()");
        return data;
    }



    @ApiOperation(value = "get all sample entity instances", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @ThrowsExceptions(value = {
            @ThrowsException(status = HttpStatus.NOT_FOUND, exception= ResourceNotFoundException.class),
            @ThrowsException(status = HttpStatus.UNAUTHORIZED, exception = UnAuthorizedException.class),
            @ThrowsException(status = HttpStatus.INTERNAL_SERVER_ERROR, exception = InternalServerProblemException.class)
    })
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<List<SampleEntity>> findAll()
    {
        return ResponseEntity.ok(sampleEntityService.findAll());
    }


    @ApiOperation(value = "add a new sampleEntity ", response = SampleEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @ThrowsExceptions(value = {
            @ThrowsException(status = HttpStatus.NOT_FOUND, exception= ResourceNotFoundException.class),
            @ThrowsException(status = HttpStatus.UNAUTHORIZED, exception = UnAuthorizedException.class),
            @ThrowsException(status = HttpStatus.INTERNAL_SERVER_ERROR, exception = InternalServerProblemException.class)
    })
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<SampleEntity> create(@RequestBody(required = true) SampleEntity sampleEntity)
    {
        //logic comes here
        return ResponseEntity.ok(sampleEntityService.save(sampleEntity));
    }



    @ApiOperation(value = "add a new sampleEntity ", response = SampleEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @ThrowsExceptions(value = {
            @ThrowsException(status = HttpStatus.NOT_FOUND, exception= ResourceNotFoundException.class),
            @ThrowsException(status = HttpStatus.UNAUTHORIZED, exception = UnAuthorizedException.class),
            @ThrowsException(status = HttpStatus.INTERNAL_SERVER_ERROR, exception = InternalServerProblemException.class)
    })
    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<SampleEntity> update(@RequestBody(required = true) SampleEntity sampleEntity, @PathVariable(value = "id", required = true) long id)
    {
        sampleEntity.setId(id);
        //logic comes here
        return ResponseEntity.ok(sampleEntityService.save(sampleEntity));
    }


    @ApiOperation(value = "find a sampleEntity by id", response = SampleEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @ThrowsExceptions(value = {
            @ThrowsException(status = HttpStatus.NOT_FOUND, exception= ResourceNotFoundException.class),
            @ThrowsException(status = HttpStatus.UNAUTHORIZED, exception = UnAuthorizedException.class),
            @ThrowsException(status = HttpStatus.INTERNAL_SERVER_ERROR, exception = InternalServerProblemException.class)
    })
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<SampleEntity> findById(@PathVariable(value = "id" ,required = true) long id)
    {
        //logic comes here
        return ResponseEntity.ok(sampleEntityService.findById(id));
    }



    @ApiOperation(value = "delete a sampleEntity by id", response = Void.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @ThrowsExceptions(value = {
            @ThrowsException(status = HttpStatus.NOT_FOUND, exception= ResourceNotFoundException.class),
            @ThrowsException(status = HttpStatus.UNAUTHORIZED, exception = UnAuthorizedException.class),
            @ThrowsException(status = HttpStatus.INTERNAL_SERVER_ERROR, exception = InternalServerProblemException.class)
    })
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<Void> delete(@PathVariable(value = "id" ,required = true) long id)
    {
        //logic comes here
        sampleEntityService.delete(id);
        return ResponseEntity.noContent().build();
    }




}
