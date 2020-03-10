package af.gov.anar.dck.odk.api;


import af.gov.anar.dck.form.model.Form;
import af.gov.anar.dck.form.service.FormService;
import af.gov.anar.dck.infrastructure.exception.SubmissionException;

import af.gov.anar.dck.infrastructure.util.ExceptionUtil;
import af.gov.anar.dck.infrastructure.util.XmlParserUtil;
import af.gov.anar.dck.instance.model.Instance;
import af.gov.anar.dck.instance.service.InstanceService;
import af.gov.anar.dck.useradministration.model.Environment;
import af.gov.anar.dck.useradministration.model.User;
import af.gov.anar.dck.useradministration.service.EnvironmentService;
import af.gov.anar.dck.useradministration.service.UserService;
import af.gov.anar.dck.odk.service.ODKService;
import af.gov.anar.dck.infrastructure.util.EmailUtil;
import af.gov.anar.dck.infrastructure.util.ExceptionUtil;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

@RestController
@RequestMapping("/odk")
public class ODKController {
    
	Logger logger = LoggerFactory.getLogger(ODKController.class);

	@Value("${app.form.instance.dir}")
	private String formInstanceDir;

	@Value("${spring.mail.to}")
    private String emailTo;
			
	@Autowired
    private FormService formService;

	@Autowired
	private ODKService odkService;
	
	@Autowired
    private UserService userService;

	@Autowired
	private EmailUtil emailUtil;

	@Autowired
	private ExceptionUtil exceptionUtil;

	private String failureEmailSub = "500 - ASIMS Exception - Instance";
	
	@RequestMapping(value="/forms/{id}/xml", method = RequestMethod.GET, produces = { "text/xml" })
    public String getFormContent(@PathVariable("id") Long id) throws IOException {
		String xmlContent = formService.findById(id).getXmlContent();
		// User user = userService.getLoggedInUser();
		return xmlContent;
    }
	
	@RequestMapping(value="/formList", method = RequestMethod.GET, produces = { "text/xml" })
	public String listForms(HttpServletRequest request) {
		logger.debug("Getting loggedin file");
		 
		List<Form> fileList = formService.findAllByGroup();
		
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder;
		String xmlTreeAsString = "";

		try {
			docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.newDocument();
			Element formsEl = doc.createElement("forms");
			doc.appendChild(formsEl);
			
			String serverAddress = request.getScheme() + "://" + request.getServerName() + ":"
	                + request.getServerPort();
			
			// System.out.println("List  "+fileList);
			for(int i = 0 ; i < fileList.size() ; i++) {
				Form form = fileList.get(i);
				String formName = form.getName();
				String formURL = serverAddress + "/odk/forms/" + form.getId() + "/xml";
				
				Element formEl = doc.createElement("form");
				Attr urlAttr = doc.createAttribute("url");
				
				urlAttr.setValue(formURL);
				formEl.setAttributeNode(urlAttr);
				formEl.appendChild(doc.createTextNode(formName));
				formsEl.appendChild(formEl);
			}
			
			xmlTreeAsString = printXMLDoc(doc);
			
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return xmlTreeAsString;
	}
	
	private String printXMLDoc(Document doc) {
		String tree = "";
		try {
			DOMSource domSource = new DOMSource(doc);
			StringWriter writer = new StringWriter();
			StreamResult result = new StreamResult(writer);
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer transformer = tf.newTransformer();
			transformer.transform(domSource, result);
			
			tree = writer.toString();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tree;
	}
	
	@RequestMapping(value="/submission", method = { RequestMethod.HEAD }, produces = { "text/xml" })
	public void odkSubmissionHead(HttpServletResponse response) {
		logger.debug("Entry odkSubmission [/odk/submission] HEAD");
		
		response.setStatus(204);
	}
	
	@RequestMapping(value="/submission", method = { RequestMethod.POST }, produces = { "text/xml" })
	public String odkSubmission(@RequestParam MultiValueMap<String, MultipartFile> files, HttpServletRequest request, HttpServletResponse response){
		logger.debug("Entry odkSubmission [/odk/submission]");
		String responseBody = "";
		User currentLoggedInUser = userService.getLoggedInUser();
		try {
			odkService.processODKSubmission(files, currentLoggedInUser, false);
			responseBody = "<OpenRosaResponse xmlns=\"http://openrosa.org/http/response\"><message nature=\"submit_success\">Your form is successfully processed by ASIMS</message></OpenRosaResponse>";
			System.out.println("Controller > odkSubmission is success");
			response.setStatus(201);
		} catch (SubmissionException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			String errorStr = ExceptionUtils.getStackTrace(e);
			File attachment = odkService.getXMLFileFromSubmission(files);
			emailUtil.sendMessageWithDetails(emailTo, "500 - ASIMS Exception - ODK Submission", errorStr, attachment, currentLoggedInUser, null, request);
			responseBody = "<OpenRosaResponse xmlns=\"http://openrosa.org/http/response\"><message nature=\"submit_success\">Failed to be processed by ASIMS</message></OpenRosaResponse>";
			response.setStatus(500);
		}
		
		return responseBody;
	}
	
}
