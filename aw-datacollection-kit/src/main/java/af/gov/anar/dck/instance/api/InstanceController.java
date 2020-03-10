package af.gov.anar.dck.instance.api;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ObjectNode;

import af.gov.anar.dck.common.auth.InstanceAuthService;
import af.gov.anar.dck.common.auth.UserAuthService;
import af.gov.anar.dck.notification.websocket.dto.Notification;
import af.gov.anar.dck.form.model.Form;
import af.gov.anar.dck.infrastructure.logger.Loggable;
import af.gov.anar.dck.infrastructure.util.ExceptionUtil;
import af.gov.anar.dck.infrastructure.util.enumeration.InstanceHistoryStatus;
import af.gov.anar.dck.instance.model.Instance;
import af.gov.anar.dck.instance.model.InstanceAttachment;
import af.gov.anar.dck.instance.model.InstanceComment;
import af.gov.anar.dck.instance.model.InstanceHistory;
import af.gov.anar.dck.instance.model.InstanceTransition;
import af.gov.anar.dck.instance.model.InstanceWatcher;
import af.gov.anar.dck.instance.service.InstanceAttachmentService;
import af.gov.anar.dck.instance.service.InstanceCommentService;
import af.gov.anar.dck.instance.service.InstanceHistoryService;
import af.gov.anar.dck.instance.service.InstanceService;
import af.gov.anar.dck.instance.service.InstanceTransitionService;
import af.gov.anar.dck.instance.service.InstanceWatcherService;
import af.gov.anar.dck.useradministration.model.CustomUser;
import af.gov.anar.dck.useradministration.model.User;
import af.gov.anar.dck.useradministration.service.UserService;
import af.gov.anar.dck.workflow.model.Workflow;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.imgscalr.Scalr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

@RestController
@RequestMapping({ "/api/instances" })
public class InstanceController {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Value("${app.upload.dir}")
	private String uploadDir;

	@Value("${spring.mail.to}")
	private String emailTo;

	@Autowired
	private InstanceAuthService instanceAuthService;

	@Autowired
	private InstanceService instanceService;

	@Autowired
	private UserAuthService userAuthService;

	@Autowired
	private UserService userService;

	@Autowired
	private InstanceCommentService instanceCommentService;

	@Autowired
	private InstanceWatcherService instanceWatcherService;

	@Autowired
	private InstanceAttachmentService instanceAttachmentService;

	@Autowired
	private InstanceHistoryService instanceHistoryService;

	@Autowired
	private InstanceTransitionService instanceTransitionService;

	@Autowired
	private ExceptionUtil exceptionUtil;

	private String failureEmailSub = "500 - ASIMS Exception - Instance";

	ObjectMapper mapper = new ObjectMapper();

	InstanceController() {
		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
	}

	@PostMapping(path = { "/{formId}" })
	public Instance create(@PathVariable("formId") Long formId, HttpServletRequest request) throws Exception {
		logger.info("Entry InstanceController>create() - POST");
		User currentLoggedInUser = userService.getLoggedInUser();
		try {
			return instanceService.create(formId);
		} catch (Exception e) {
			exceptionUtil.notifyDevTeam(failureEmailSub, e, currentLoggedInUser, userService.getCurrentEnv(), null, request, false);
		}
		return null;
	}

	@PostMapping(path = { "/{instanceId}/comments" })
	public InstanceComment createComment(@PathVariable("instanceId") Long instanceId, @RequestBody String comment,
			HttpServletRequest request) throws Exception {
		logger.info("Entry InstanceController>createComment() - POST");
		User currentLoggedInUser = userService.getLoggedInUser();
		try {
			return instanceCommentService.create(instanceService.findById(instanceId), comment);
		} catch (Exception e) {
			exceptionUtil.notifyDevTeam(failureEmailSub, e, currentLoggedInUser, userService.getCurrentEnv(), null, request, false);
		}
		return null;
	}

	@PostMapping(path = { "/watchers/create/{instanceId}" }, produces = MediaType.APPLICATION_JSON_VALUE)
	public InstanceWatcher createWatcher(@PathVariable(value = "instanceId", required = true) Long instanceId,
			@RequestBody(required = true) String watcherId, HttpServletRequest request) throws Exception {
		logger.info("Entry InstanceController>createWatcher() - POST");
		User currentLoggedInUser = userService.getLoggedInUser();
		try {
			return instanceWatcherService.create(instanceService.findById(instanceId), Long.valueOf(watcherId));
		} catch (Exception e) {
			exceptionUtil.notifyDevTeam(failureEmailSub, e, currentLoggedInUser, userService.getCurrentEnv(), null, request, false);
			return null;
		}
	}

	@GetMapping(value = "/watchers/{instanceId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<List<InstanceWatcher>> findAllInstanceWathers(
			@PathVariable(value = "instanceId", required = true) Long instanceId) {
		return ResponseEntity.ok(instanceWatcherService.findAllByInstance(instanceId));
	}

	@PostMapping(path = { "/{formId}/{instanceId}/attachments" })
	public InstanceAttachment createAttachment(Authentication authentication,
			@PathVariable("instanceId") Long instanceId, @PathVariable("formId") Long formId,
			@RequestParam(value = "instanceFile", required = true) MultipartFile file, HttpServletRequest request)
			throws Exception {
		User currentLoggedInUser = userService.getLoggedInUser();
		try {
			CustomUser userDetails = (CustomUser) authentication.getPrincipal();
			String uploadAvatarDir = uploadDir + File.separator + userDetails.getCurrentEnv() + "/instances/"
					+ formId.toString() + File.separator + instanceId.toString() + "/attachments/";

			Format formatter = new SimpleDateFormat("yyyy-MM-dd_HH_mm_ss");
			String fileName = formatter.format(Calendar.getInstance().getTime());

			if (!file.isEmpty()) {
				try {
					Instance instance = instanceService.findById(instanceId);
					instance.getForm().getId();
					String saveDirectory = uploadAvatarDir;
					File newFile = new File(saveDirectory);
					if (!newFile.exists()) {
						newFile.mkdirs();
					}

					String completeFilePathName = saveDirectory + fileName + "_" + file.getOriginalFilename();

					byte[] bytes = file.getBytes();
					Path path = Paths.get(completeFilePathName);
					Files.write(path, bytes);

					System.out.println("Image Saved::: " + fileName);
					return instanceAttachmentService.create(instance, completeFilePathName);
				} catch (Exception e) {
					e.printStackTrace();
					return null;
				}
			}
			return null;
		} catch (Exception e) {
			exceptionUtil.notifyDevTeam(failureEmailSub, e, currentLoggedInUser, userService.getCurrentEnv(), null, request, false);
			return null;
		}
	}

	@GetMapping(path = { "/{formId}/blankinstance" })
	public String getBlankInstance(@PathVariable("formId") Long formId, HttpServletRequest request) throws Exception {
		User currentLoggedInUser = userService.getLoggedInUser();
		try {
			return instanceService.getBlankInstance(formId);
		} catch (Exception e) {
			exceptionUtil.notifyDevTeam(failureEmailSub, e, currentLoggedInUser, userService.getCurrentEnv(), null, request, false);
			return null;
		}
	}

	@PostMapping(path = { "/{instanceId}/cloneInstance" })
	public Instance cloneInstance(@PathVariable("instanceId") Long instanceId, HttpServletRequest request) throws Exception {
		logger.info("Entry InstanceController>create() - POST");
		User currentLoggedInUser = userService.getLoggedInUser();
		try {
			    return instanceService.cloneInstance(instanceId, currentLoggedInUser);
		} catch (Exception e) {
			exceptionUtil.notifyDevTeam(failureEmailSub, e, currentLoggedInUser, userService.getCurrentEnv(), null, request, false);
		}
		return null;
	}

	@RequestMapping(value = "/attachment/{attachmentId}", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
	public ResponseEntity<Resource> getAttachment(HttpServletResponse response, HttpServletRequest request,
			@PathVariable("attachmentId") Long attachmentId) throws Exception {
		logger.info("Entry InstanceController>getAttachment() - GET");
		User currentLoggedInUser = userService.getLoggedInUser();
		try {
			InstanceAttachment instanceAttachment = instanceAttachmentService.findById(attachmentId);
			try {
				String attachmentPath = instanceAttachment.getAttachment();
				File file = new File(attachmentPath);

				if (file.exists()) {
					Resource resource = instanceService.loadFileAsResource(attachmentPath);
					// InputStreamSource resource = new InputStreamResource(is);
					String contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
					if (contentType == null) {
						contentType = "application/octet-stream";
					}

					return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
							.header(HttpHeaders.CONTENT_DISPOSITION,
									"attachment; filename=\"" + resource.getFilename() + "\"")
							.body(resource);

				} else {
					return null;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		} catch (Exception e) {
			exceptionUtil.notifyDevTeam(failureEmailSub, e, currentLoggedInUser, userService.getCurrentEnv(), null, request, false);
			return null;
		}
	}

	@DeleteMapping(path = { "/attachments/{id}" })
	public boolean deleteAttachement(@PathVariable("id") Long attachemenId, HttpServletRequest request)
			throws Exception {
		User currentLoggedInUser = userService.getLoggedInUser();
		try {
			return instanceAttachmentService.delete(attachemenId);
		} catch (Exception e) {
			exceptionUtil.notifyDevTeam(failureEmailSub, e, currentLoggedInUser, userService.getCurrentEnv(), null, request, false);
			return false;
		}
	}

	@GetMapping(path = { "/{instanceId}/history" })
	public List getInstanceHistory(@PathVariable("instanceId") Long instanceId, HttpServletRequest request)
			throws Exception {
		User currentLoggedInUser = userService.getLoggedInUser();
		try {
			return instanceHistoryService.findAllByInstance(instanceId);
		} catch (Exception e) {
			exceptionUtil.notifyDevTeam(failureEmailSub, e, currentLoggedInUser, userService.getCurrentEnv(), null, request, false);
			return null;
		}
	}

	@GetMapping(path = { "/hasChildren" })
	public List<Instance> hasChildren(HttpServletRequest request)
			throws Exception {
		logger.info("Entry InstanceController>getChildren() - GET");
		User currentLoggedInUser = userService.getLoggedInUser();
		try {
			return instanceService.hasChildren();
		} catch (Exception e) {
			exceptionUtil.notifyDevTeam(failureEmailSub, e, currentLoggedInUser, userService.getCurrentEnv(), null, request, false);
			return null;
		}
	}

	@GetMapping(path = { "/{instanceId}/children" })
	public List<Instance> getChildren(@PathVariable("instanceId") Long instanceId, HttpServletRequest request)
			throws Exception {
		logger.info("Entry InstanceController>getChildren() - GET");
		User currentLoggedInUser = userService.getLoggedInUser();
		try {
			return instanceService.getChildInstances(instanceId);
		} catch (Exception e) {
			exceptionUtil.notifyDevTeam(failureEmailSub, e, currentLoggedInUser, userService.getCurrentEnv(), null, request, false);
			return null;
		}
	}

	@Loggable
	@GetMapping(path = { "/{instanceId}/name" })
	public String getFormNameById(@PathVariable("instanceId") Long instanceId, HttpServletRequest request)
			throws Exception {
		// return instanceService.findAllByForm(formId);
		User currentLoggedInUser = userAuthService.getLoggedInUser();
		try {
			Instance instance = instanceAuthService.findById(instanceId);
			return instance.getTitle();
		} catch (Exception e) {
			exceptionUtil.notifyDevTeam(failureEmailSub, e, currentLoggedInUser, userAuthService.getCurrentEnv(), null, request, false);
			return null;
		}
	}

	@PostMapping(path = { "/{parentInstanceId}/child/{childFormId}" })
	public Instance createChild(@PathVariable("parentInstanceId") Long parentInstanceId,
			@PathVariable("childFormId") Long childFormId, @RequestBody String formName, HttpServletRequest request)
			throws Exception {
		logger.info("Entry InstanceController>createChild() - POST");
		User currentLoggedInUser = userService.getLoggedInUser();
		try {
			return instanceService.create(childFormId, parentInstanceId, formName);
		} catch (Exception e) {
			exceptionUtil.notifyDevTeam(failureEmailSub, e, currentLoggedInUser, userService.getCurrentEnv(), null, request, false);
			return null;
		}
	}

	@PutMapping(path = { "/{id}/detach" })
	public boolean detachInstance(@PathVariable("id") Long id, @RequestBody String payload, 
            HttpServletRequest request) throws Exception {
		logger.info("Entry InstanceController>detachInstance() - PUT");
		User currentLoggedInUser = userService.getLoggedInUser();
		try {
			return instanceService.detach(id);
		} catch (Exception e) {
			exceptionUtil.notifyDevTeam(failureEmailSub, e, currentLoggedInUser, userService.getCurrentEnv(), null, request, false);
			return false;
		}
	}

	@GetMapping(path = { "/{id}" })
	public ObjectNode findOne(@PathVariable("id") Long instanceId, HttpServletRequest request) throws Exception {
		logger.info("Entry InstanceController>findOne() - GET");
		User currentLoggedInUser = userService.getLoggedInUser();
		try {
			Instance instance = instanceService.findById(instanceId);
			instance.getOwner();
			Form form = instance.getForm();

			JsonNode instanceJson = mapper.convertValue(instance, JsonNode.class);
			JsonNode formJson = mapper.convertValue(form.getXmlContent(), JsonNode.class);
			JsonNode formGridJson = mapper.convertValue(form.getGridJson(), JsonNode.class);

			ObjectNode instanceObj = mapper.createObjectNode();
			instanceObj.set("instance", instanceJson);

			Long parentInstanceId = null;
			Instance parentInstance = instance.getParentInstance();
			if (parentInstance != null) {
				parentInstanceId = parentInstance.getId();
			}

			Workflow instanceWorkflow = form.getWorkflow();
			Long workflowId = null;
			if(instanceWorkflow != null) {
				workflowId = instanceWorkflow.getId();
			}

			instanceObj.set("parentInstanceId", mapper.convertValue(parentInstanceId, JsonNode.class));
			instanceObj.set("form", formJson);
			instanceObj.set("formGridJson", formGridJson);
			instanceObj.set("formId", mapper.convertValue(form.getId(), JsonNode.class));
			instanceObj.set("workflowId", mapper.convertValue(workflowId, JsonNode.class));

			return instanceObj;
		} catch (Exception e) {
			exceptionUtil.notifyDevTeam(failureEmailSub, e, currentLoggedInUser, userService.getCurrentEnv(), null, request, false);
			return null;
		}
	}

	@PutMapping(path = { "/{id}" })
	public boolean update(@PathVariable("id") Long id, @RequestBody String payload, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.info("Entry InstanceController>update() - PUT");
		User currentLoggedInUser = userService.getLoggedInUser();
		try {
			boolean notifyWatchers = true;
			return instanceService.update(id, payload, notifyWatchers);

		} catch (Exception e) {
			exceptionUtil.notifyDevTeam(failureEmailSub, e, currentLoggedInUser, userService.getCurrentEnv(), null, request, false);
			return false;
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public boolean delete(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") Long id)
			throws Exception {
		logger.info("Entry InstanceController>delete() - PUT");
		User currentLoggedInUser = userService.getLoggedInUser();
		try {
			return instanceService.delete(id);
		} catch (Exception e) {
			exceptionUtil.notifyDevTeam(failureEmailSub, e, currentLoggedInUser, userService.getCurrentEnv(), null, request, false);
			return false;
		}
	}

	// deleting image from instance edit form
	@DeleteMapping(value = "/{instanceId}/deleteImage")
	public boolean deleteImage(Authentication authentication, HttpServletRequest request,
			@PathVariable("instanceId") Long instanceId,
			@RequestParam(value = "selectFile", required = true) String imageName,
			@RequestParam(value = "fieldName", required = true) String fieldName,
			@RequestParam(value = "isRepeatField", required = true) boolean isRepeatField) throws Exception {
		Instance instance = instanceService.findById(instanceId);

		instanceService.deleteImage(instance, imageName, fieldName, isRepeatField);

		String instanceDir = instance.getInstanceFolderName();
		CustomUser userDetails = (CustomUser) authentication.getPrincipal();
		User user = userService.findByUsername(userDetails.getUsername());

		if (!imageName.isEmpty()) {
			try {

				String completeFilePathName = instanceDir + File.separator + imageName;

				Path path = Paths.get(completeFilePathName);
				Files.delete(path);

			} catch (Exception e) {
				exceptionUtil.notifyDevTeam(failureEmailSub, e, user, userDetails.getCurrentEnv(), null, request, false);
				return false;
			}
		}
		return true;
	}

	// uploading image to the instance in edit form
	@PostMapping(value = "/{instanceId}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public boolean addImage(Authentication authentication, HttpServletRequest request,
			@RequestParam(value = "file", required = true) MultipartFile imageFile,
			@PathVariable("instanceId") Long instanceId,
			@RequestParam(value = "fieldName", required = true) String fieldName,
			@RequestParam(value = "isRepeatField", required = true) boolean isRepeatField) throws Exception {
		User currentLoggedInUser = userService.getLoggedInUser();
		try {
			Instance instance = instanceService.findById(instanceId);
			// every instance we create must have folder created and the location should be
			// saved with instance record
			String instanceDir = instance.getInstanceFolderName();

			Format formatter = new SimpleDateFormat("yyyy-MM-dd_HH_mm_ss");
			String fileName = formatter.format(Calendar.getInstance().getTime());
			String fileExt = FilenameUtils.getExtension(imageFile.getOriginalFilename());
			String fileNameWithoutExt = FilenameUtils.removeExtension(imageFile.getOriginalFilename());

			if (!imageFile.isEmpty()) {
				try {
					String completeFilePathName = instanceDir + File.separator + fileNameWithoutExt + "_" + fileName
							+ "." + fileExt;

					byte[] bytes = imageFile.getBytes();
					Path path = Paths.get(completeFilePathName);
					Files.write(path, bytes);

					File savedImageFile = new File(completeFilePathName);
					instanceService.addImage(instance, fieldName, savedImageFile, isRepeatField);
					return true;
				} catch (Exception e) {
					exceptionUtil.notifyDevTeam(failureEmailSub, e, currentLoggedInUser, userService.getCurrentEnv(), null, request, false);
					return false;
				}
			}
			return false;
		} catch (Exception e) {
			exceptionUtil.notifyDevTeam(failureEmailSub, e, currentLoggedInUser, userService.getCurrentEnv(), null, request, false);
			return false;
		}
	}

	@RequestMapping(value = "/{instanceId}/image/{image}", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
	public @ResponseBody byte[] getImage(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("instanceId") Long id, @PathVariable("image") String image) throws Exception {
		logger.info("Entry InstanceController>getImage() - GET");
		User currentLoggedInUser = userService.getLoggedInUser();
		try {
			Instance instance = instanceService.findById(id);

			String imagePath = instance.getInstanceFolderName() + File.separator + image;
			if (new File(imagePath).exists()) {
				return IOUtils.toByteArray(new FileInputStream(imagePath));
			} else {
				return null;
			}
		} catch (Exception e) {
			exceptionUtil.notifyDevTeam(failureEmailSub, e, currentLoggedInUser, userService.getCurrentEnv(), null, request, false);
			return null;
		}
	}

	@RequestMapping(value = "/{id}/image/{image}/timg", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
	public @ResponseBody byte[] getImageThumbnail(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("id") Long id, @PathVariable("image") String image) throws Exception {
		User currentLoggedInUser = userService.getLoggedInUser();
		try {
			logger.info("Entry InstanceController>getImageThumbnail() - GET");
			Instance instance = instanceService.findById(id);

			String imagePath = instance.getInstanceFolderName() + File.separator + image;
			if (new File(imagePath).exists()) {

				BufferedImage bufferedImage = ImageIO.read(new FileInputStream(imagePath));
				BufferedImage thumbnail = Scalr.resize(bufferedImage, 250);

				ByteArrayOutputStream bos = new ByteArrayOutputStream();
				ImageIO.write(thumbnail, "jpg", bos);

				return bos.toByteArray();
			} else {
				return null;
			}
		} catch (Exception e) {
			exceptionUtil.notifyDevTeam(failureEmailSub, e, currentLoggedInUser, userService.getCurrentEnv(), null, request, false);
			return null;
		}
	}

	@RequestMapping(value = "/{id}/file/{file}", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
	public @ResponseBody byte[] getFile(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("id") Long id, @PathVariable("file") String file) throws Exception {
		logger.info("Entry InstanceController>getFile() - GET");
		User currentLoggedInUser = userService.getLoggedInUser();
		try {
			Instance instance = instanceService.findById(id);

			String imagePath = instance.getInstanceFolderName() + File.separator + file;
			if (new File(imagePath).exists()) {
				return IOUtils.toByteArray(new FileInputStream(imagePath));
			} else {
				return null;
			}
		} catch (Exception e) {
			exceptionUtil.notifyDevTeam(failureEmailSub, e, currentLoggedInUser, userService.getCurrentEnv(), null, request, false);
			return null;
		}
	}

	@Loggable
	@GetMapping(value = { "/transitions/{instanceId}" }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public List<InstanceTransition> findInstanceTransitionsByInstanceId(
			@PathVariable(value = "instanceId", required = true) Long instanceId, HttpServletRequest request)
			throws Exception {
		User currentLoggedInUser = userService.getLoggedInUser();
		try {
			return instanceTransitionService.findByInstanceIdBrief(instanceId);
		} catch (Exception e) {
			exceptionUtil.notifyDevTeam(failureEmailSub, e, currentLoggedInUser, userService.getCurrentEnv(), null, request, false);
			return null;
		}
	}

	@GetMapping(path = { "/{instanceId}/reports/{reportId}" }, produces = MediaType.APPLICATION_PDF_VALUE)
	public @ResponseBody byte[] getReport(HttpServletRequest request, HttpServletResponse response,
		    @PathVariable("instanceId") Long instanceId, @PathVariable("reportId") Long reportId) 
            throws Exception {
		logger.info("Entry InstanceController>getReport() - POST");
		User currentLoggedInUser = userService.getLoggedInUser();
		try {
			File pdfFile = instanceAuthService.generatePdfJasperReport(instanceId, reportId);

			FileInputStream fin = new FileInputStream(pdfFile);
			return IOUtils.toByteArray(fin);
		} catch (Exception e) {
			exceptionUtil.notifyDevTeam(failureEmailSub, e, currentLoggedInUser, userService.getCurrentEnv(), null, request, false);
			return null;
		}
	}

	@GetMapping(path = { "/notifications" }, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<List<Notification>> getNotifications() 
    {
		return ResponseEntity.ok(instanceHistoryService.findByInstanceHistoryStatus(InstanceHistoryStatus.UNVIEWED));
	}

	@GetMapping(path = { "/notifications/{instanceHistoryId}" }, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<InstanceHistory> getInstanceHistoryById(
		    @PathVariable(name = "instanceHistoryId", required = true) long instanceHistoryId) {

		return ResponseEntity.ok(instanceHistoryService.findById(instanceHistoryId));
	}

	@GetMapping(path = {"/notifications/changestatus/{instanceHistoryId}" }, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<InstanceHistory> changeInstanceHistoryStatus(
		@PathVariable(name = "instanceHistoryId", required = true) long instanceHistoryId) {

		InstanceHistory instanceHistory = instanceHistoryService.findById(instanceHistoryId);
		instanceHistory.setInstanceHistoryStatus(InstanceHistoryStatus.VIEWED);
		instanceHistoryService.create(instanceHistory);

		return ResponseEntity.ok(instanceHistory);
	}
}
