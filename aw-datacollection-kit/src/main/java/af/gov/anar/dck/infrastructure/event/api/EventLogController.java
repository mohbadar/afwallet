package af.gov.anar.dck.infrastructure.event.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import af.gov.anar.dck.infrastructure.event.model.EventLog;
import af.gov.anar.dck.infrastructure.event.service.EventLogServiceImpl;
import af.gov.anar.dck.infrastructure.logger.Loggable;

import java.util.List;

@RestController
@RequestMapping({"/api/eventlogs"})
public class EventLogController{

    @Autowired private EventLogServiceImpl eventLogService;

    @Loggable
    @GetMapping(value = { "" }, produces = { MediaType.APPLICATION_JSON_VALUE })
    public @ResponseBody ResponseEntity<List<EventLog>> findAll()
    {
        return ResponseEntity.ok(eventLogService.findAll());
    }


    @Loggable
    @GetMapping(value = { "/{id}" }, produces = { MediaType.APPLICATION_JSON_VALUE })
    public @ResponseBody ResponseEntity<EventLog> findByid(@PathVariable(value = "id", required = true) long id)
    {
        return ResponseEntity.ok(eventLogService.findById(id));
    }


    @Loggable
    @GetMapping(value = { "/user/{userId}" }, produces = { MediaType.APPLICATION_JSON_VALUE })
    public @ResponseBody ResponseEntity<List<EventLog>> findByUser(@PathVariable(value = "userId", required = true) long id)
    {
        return ResponseEntity.ok(eventLogService.findByUser(id));
    }


    @Loggable
    @GetMapping(value = { "/level/{level}" }, produces = { MediaType.APPLICATION_JSON_VALUE })
    public @ResponseBody ResponseEntity<List<EventLog>> findByUser(@PathVariable(value = "level", required = true) String level)
    {
        return ResponseEntity.ok(eventLogService.findByLoggerLevel(level));
    }

}