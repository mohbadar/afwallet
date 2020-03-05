package af.gov.anar.ebreshna.configuration.metering.meter_status;

import af.gov.anar.api.handler.ResponseHandler;
import af.gov.anar.ebreshna.configuration.billing.behaviour.BehaviourConfiguration;
import af.gov.anar.ebreshna.configuration.billing.behaviour.BehaviourService;
import af.gov.anar.ebreshna.configuration.common.province.Province;
import af.gov.anar.ebreshna.configuration.common.province.ProvinceService;
import af.gov.anar.ebreshna.configuration.metering.meter_status.MeterStatusMaster;
import af.gov.anar.ebreshna.configuration.metering.meter_status.MeterStatusService;
import af.gov.anar.ebreshna.infrastructure.service.UserService;
import af.gov.anar.ebreshna.metering.meter.Meter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/config/metering/meter-status-masters")
public class MeterStatusController extends ResponseHandler {

    @Autowired
    private MeterStatusService service;

    @Autowired
    private UserService userService;

    @Autowired
    private BehaviourService behaviourService;

    ObjectMapper mapper = new ObjectMapper();


    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<List<MeterStatusMaster>> findall()
    {
        return ResponseEntity.ok(service.findall());
    }


    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<MeterStatusMaster> findOne(@PathVariable(name = "id", required = true) long id)
    {
        return ResponseEntity.ok(service.findOne(id));
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<MeterStatusMaster> update(@PathVariable(name = "id", required = true) long id, @RequestBody(required = true) MeterStatusMaster obj)
    {
        obj.setId(id);
        return ResponseEntity.ok(service.save(obj));
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<MeterStatusMaster> save(@Validated  @RequestBody(required = true) String request) throws JsonProcessingException {
        JsonNode root = mapper.readTree(request);

        Long behaviourId =root.get("behaviourConfiguration").asLong();
        BehaviourConfiguration behaviourConfiguration = behaviourService.findOne(behaviourId);

        MeterStatusMaster obj = MeterStatusMaster.builder()
                .meterStatus(root.get("meterStatus").asText())
                .meterStatusCode(root.get("meterStatusCode").asText())
                .behaviourConfiguration(behaviourConfiguration)
                .shortName(root.get("shortName").asText())
                .remark(root.get("remark").asText())
                .billingBasis(root.get("billingBasis").asText())
                .govtLetter(root.get("govtLetter").asText())
                .build();

        return ResponseEntity.ok(service.save(obj));
    }

}
