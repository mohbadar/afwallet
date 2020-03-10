package af.gov.anar.dck.notification.sms.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import af.gov.anar.dck.common.auth.UserAuthService;
import af.gov.anar.dck.notification.sms.model.Sms;
import af.gov.anar.dck.notification.sms.service.SmsService;
import af.gov.anar.dck.useradministration.model.Environment;
import af.gov.anar.dck.useradministration.model.User;
import af.gov.anar.dck.useradministration.service.EnvironmentService;
import af.gov.anar.dck.useradministration.service.UserService;
import af.gov.anar.dck.infrastructure.util.DateTimeUtil;
import af.gov.anar.dck.infrastructure.util.ExceptionUtil;
import af.gov.anar.dck.infrastructure.util.JsonParserUtil;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/sms")
public class SmsController {

    Logger logger = LoggerFactory.getLogger(SmsController.class);

    @Autowired
    private UserAuthService userAuthService;

    @Autowired
    private UserService userService;

    @Autowired
    private SmsService smsService;

    @Autowired
    private EnvironmentService environmentService;

    @Autowired
    private JsonParserUtil jsonParserUtil;

    @Autowired
    private DateTimeUtil dateTimeUtil;
    
    @Autowired
	private ExceptionUtil exceptionUtil;
    
    private String failureEmailSub = "500 - ASIMS Exception - SMS";

    ObjectMapper mapper = new ObjectMapper();
    /*
     *  @param input Datatables input
     *  returns all the sms, recieved and sent
     */
    @PostMapping
    public Object findAll(@Valid @RequestBody DataTablesInput input, HttpServletRequest request)
            throws Exception {
        logger.info("Entry SmsController>findAll() - GET");
        User currentLoggedInUser = userAuthService.getLoggedInUser();
        DataTablesOutput<Sms> output = new DataTablesOutput<>();
        
        try {
            Object returnedObj = smsService.findAllByEnv(input);
            return returnedObj;
        } catch (Exception e) {
            System.out.println(">>>> the exception message:" + e.toString());
            exceptionUtil.notifyDevTeam(failureEmailSub, e, currentLoggedInUser, userAuthService.getCurrentEnv(), null, request, false);
            return null;
        }
    }

    /*
     * returns a summery of the sms, total recieved, total sent
     *
     */

    @PostMapping(value = "/sync/push")
    public boolean storeSMS(@RequestBody String payload, HttpServletRequest request) throws Exception {
        logger.debug("SMS Content: ", payload);
        User currentLoggedInUser = null;
        String envSlug = null;
        try {
            JSONObject jsonObj = jsonParserUtil.parse(payload);

            String username = jsonObj.getString("username");
            User user = userService.findByUsername(username);
            
            String secretKey = jsonObj.getString("secret");
            Environment environment = environmentService.findBySecretKey(secretKey);
            if(environment == null && user == null) {
                return false;
            }

			List<Sms> smsList = new ArrayList<Sms>();
			JSONArray messages = jsonObj.getJSONArray("messages");
			for (int i = 0; i < messages.length(); i++) {
				JSONObject messageObj = messages.getJSONObject(i);
				Sms newSms = new Sms();
				envSlug = environment.getSlug();
                newSms.setEnvSlug(envSlug);
                newSms.setUserId(user.getId());
				
				if (!messageObj.isNull("from")) {
					newSms.setSender(messageObj.getString("from"));
				}
				if (!messageObj.isNull("sent_to")) {
					newSms.setReceiver(messageObj.getString("sent_to"));
				}
				if (!messageObj.isNull("message")) {
					newSms.setText(messageObj.getString("message"));
				}
				if (!messageObj.isNull("device_id")) {
					newSms.setDeviceId(messageObj.getString("device_id"));
				}
				if (!messageObj.isNull("sent_timestamp")) {
					newSms.setSentTimestamp(dateTimeUtil.convertStringToLocalDateTime(messageObj.getString("sent_timestamp")));
				}
				smsList.add(newSms);
			}
			
			if(smsList.size() > 0) {
				smsService.create_batch(smsList);
				return true;
			} else {
				return false;
			}
        } catch (Exception e) {
            System.out.println(">>>> the exception message:" + e.toString());
            exceptionUtil.notifyDevTeam(failureEmailSub, e, currentLoggedInUser, envSlug, null, request, false);
            return false;
        }
    }

    /*
     * Creating a new Sms
     */
    @PostMapping(path = "{/summery}")
    public Sms create(@Valid @RequestBody Sms sms) {
        logger.info("Entry SmsController>create() - POST");
        return this.smsService.create(sms);
    }

    /*
    * return the sms by it's id 
     * @Params id  id of the sms
     */
    @GetMapping(path = { "/{id}" })
    public Sms findOne(@PathVariable("id") Long id) {
        logger.info("Entry SmsController>findOne() - GET");
        return this.smsService.findById(id);
    }

}