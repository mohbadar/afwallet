package af.gov.anar.dck.odk.service;

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

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.Document;

import java.io.*;
import java.nio.file.Files;
import java.sql.Timestamp;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

@Service
public class ODKServiceImpl implements ODKService {

	Logger logger = LoggerFactory.getLogger(this.getClass());
    	
	@Value("${app.upload.dir}")
    private String uploadDir;
    
	@Value("${app.form.submission.dir}")
    private String odkSubmissionDir;
	
	@Value("${app.form.instance.dir}")
	private String formInstanceDir;
	
	private String EXCEPTION_FILENAME = "exception_log";
	
	@Autowired
    protected EnvironmentService environmentService;
	
	@Autowired
    private FormService formService;
	
	@Autowired
    protected InstanceService instanceService;
	
	@Autowired
    private UserService userService;
	
	@Autowired
    private XmlParserUtil xmlParserUtil;
	
	public void processODKSubmission(MultiValueMap<String, MultipartFile> filesMap, User user, boolean overrideFlag) throws SubmissionException {
		System.out.println("ProcessODKSubmission multiparts");
		File uploadedFormDir = null;
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		
		try {
			String formName = "";
			
			for(String paramName : filesMap.keySet()) {
				for(MultipartFile paramFile : filesMap.get(paramName)) {
					String fn = paramFile.getOriginalFilename();
					if (fn.endsWith(".xml")){
						formName = fn.replaceAll(".xml", "");
						break;
					} else if(fn.endsWith(".zip")) {
						formName = fn.replaceAll(".zip", "");
						break;
					}
				}
			}
            
			uploadedFormDir = createFormSubmissionDirectory(odkSubmissionDir, formName + "_" + timestamp.getTime());

			for(String paramName : filesMap.keySet()) {
				for(MultipartFile paramFile : filesMap.get(paramName)) {
					String fn = paramFile.getOriginalFilename();
					if (fn.endsWith(".zip")){
						logger.debug("Expanding {} - {}", uploadedFormDir, fn);
						
						File zipFile = new File(uploadedFormDir, fn);
						paramFile.transferTo(zipFile);
						
						extractZipFile(zipFile, uploadedFormDir);
	                    zipFile.delete();
					} else {
                        System.out.println("Not .zip file");
						logger.debug("Saving: {}", fn);
						System.out.println("Saving: " + fn + ", Dir: " + uploadedFormDir.getPath());
						
						System.out.println("ParamFile does not exists. Size: " + paramFile.getSize());
						
                        File desFile = new File(uploadedFormDir, fn);
                        // if (!desFile.getParentFile().exists()) {
                        //     System.out.println("Parent Directory does not exist");
                        //     desFile.getParentFile().mkdirs();
                        // }
                        System.out.println("File Path: " + desFile.getAbsolutePath());
                        // desFile.createNewFile();

                        Files.createDirectories(desFile.getParentFile().toPath());
                        Files.createFile(desFile.toPath());

						Files.write(desFile.toPath(), paramFile.getBytes());
//						paramFile.transferTo(desFile);
					}
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
			ExceptionUtil.dumpException(e, new File(uploadedFormDir, EXCEPTION_FILENAME));
			throw new SubmissionException(e);
		}
		
		moveSubmissionToEnvironmentFolder(uploadedFormDir, user, overrideFlag);
	}
	
	public void moveSubmissionToEnvironmentFolder(File uploadedFormDir, User user, boolean overrideFlag) throws SubmissionException {
        logger.debug("moveSubmissionToEnvironmentFolder()");
        Instance instance = new Instance();
        instance.setOwner(userService.getLoggedInUser());
        
        System.out.println("Owner Name: " + instance.getOwner().getName());
        File xmlFile = null;
        for (File file : FileUtils.listFiles(uploadedFormDir, null, false)) {
            String fileName = file.getName();
            if (fileName.endsWith(".xml")) {
                xmlFile = file;
                break;
            }
        }
        
        if (xmlFile == null) {
            SubmissionException e = new SubmissionException("XML file not found in submission");
            ExceptionUtil.dumpException(e, new File(uploadedFormDir, EXCEPTION_FILENAME));
            throw e;
        }
        
        String xmlFileContent = null;
 
        //find environment
        Environment environment = null;
        Form form = null;
        try {
        	xmlFileContent = new String(Files.readAllBytes(xmlFile.toPath()));
        	Document xmlDoc = xmlParserUtil.parse(xmlFileContent);
        	String xFormId = xmlParserUtil.getInstanceXMLId(xmlDoc);
            
            String envSlug = xmlParserUtil.getXFormEnvSlug(xmlDoc);
            environment = environmentService.findBySlug(envSlug);
            instance.setEnvSlug(envSlug);

        	form = formService.findByXmlFormId(xFormId);
        	instance.setForm(form);
        	
        } catch (Exception e) {
            SubmissionException e1 = new SubmissionException("XML parsing error or no environment code defined");
            ExceptionUtil.dumpException(e1, new File(uploadedFormDir, EXCEPTION_FILENAME));
            throw e1;
        }
         
        if (environment == null) {
           SubmissionException e = new SubmissionException("Environment code not found in submission");
           ExceptionUtil.dumpException(e, new File(uploadedFormDir, EXCEPTION_FILENAME));
           throw e;
        	// environment = environmentService.findById(new Long(1));
        }

        if (form == null) {
            SubmissionException e = new SubmissionException("Form not found in submission XML");
            throw e;
        }
        
        try {
            logger.debug("parsing the instance");
            
            instance.setXmlContent(xmlFileContent);
            
            File envSubmissionDir = getInstanceDirectoryByEnvironment(uploadedFormDir.getName(), environment, form, null, true);
            
            if (!envSubmissionDir.exists()) {
                FileUtils.moveDirectory(uploadedFormDir, envSubmissionDir);
            } else {
                for (File srcFile : uploadedFormDir.listFiles()) {
                    FileUtils.moveFileToDirectory(srcFile, envSubmissionDir, false);
                }
            }
            
            instance.setInstanceFolderName(envSubmissionDir.getPath());
            
            Instance createdinstance = instanceService.create(instance);

            // here we will move the files one down folder with instanceId
            File finalInstanceDir = getInstanceDirectoryByEnvironment(envSubmissionDir.getName(), environment, form, createdinstance, true);
            // File finalInstanceDir = createDirectory(new File(envSubmissionDir + "/" + createdinstance.getId()));
            FileUtils.copyDirectory(envSubmissionDir, finalInstanceDir);
            createdinstance.setInstanceFolderName(finalInstanceDir.getPath());
            instanceService.update(createdinstance.getId(), createdinstance, null);

            deleteDirectory(envSubmissionDir);
            deleteDirectory(uploadedFormDir);
        } catch (IOException e) {
            logger.debug("Exception: ", e);
            throw new SubmissionException(e);
        }
        
        System.out.println("moveSubmissionToEnvironmentFolder is success");
    }
	
//	public Instance reProcessSubmission(File projectSubmissionDir, User user, Project project, boolean overrideFlag) throws SubmissionException, IOException, ProcessingException {
//        
//        Instance instance = submissionParser.parse(projectSubmissionDir, user, project, overrideFlag);
//         
//        if (FileUtils.getFile(projectSubmissionDir, EXCEPTION_FILENAME).exists()) {
//            logger.debug("a previous exception.txt file exists. deleting it.");
//            FileUtils.getFile(projectSubmissionDir, EXCEPTION_FILENAME).delete();
//        }
//        logger.debug("moving the report file to the processed folder");
//        File processedDir = getProjectProcessedDirectory(projectSubmissionDir.getName(), project);
//        if (processedDir.exists()) {
//            logger.debug("report with same name already in the processed folder, moving it to the deleted folder");
//            File deletedDest = new File(deletedDir, projectSubmissionDir.getName());
//            logger.debug("destination path in deleted dir: {}", deletedDest);
//            FileUtils.moveDirectory(processedDir, deletedDest);
//        }
//        FileUtils.moveDirectory(projectSubmissionDir, processedDir);
// 
//        logger.exit("processSubmission()");
//        return instance;
//    }
	
	public static File extractZipFile(File sourceZipFile, File destDir) throws IOException {
        ZipFile zipFile = new ZipFile(sourceZipFile, ZipFile.OPEN_READ);
        Enumeration<? extends ZipEntry> zipFileEntries = zipFile.entries();
        while (zipFileEntries.hasMoreElements()) {
            ZipEntry entry = zipFileEntries.nextElement();
            String currentEntry = entry.getName();
            File rawDestFile = new File(currentEntry);
            File destFile = new File(destDir, rawDestFile.getName());
            File destinationParent = destFile.getParentFile();
            destinationParent.mkdirs();
            if (!entry.isDirectory()) {
                BufferedInputStream is = new BufferedInputStream(zipFile.getInputStream(entry));
                int currentByte;
                byte data[] = new byte[2048];
                FileOutputStream fos = new FileOutputStream(destFile);
                BufferedOutputStream dest = new BufferedOutputStream(fos, 2048);
                while ((currentByte = is.read(data, 0, 2048)) != -1)
                    dest.write(data, 0, currentByte);
                dest.flush();
                dest.close();
                is.close();
            }
        }
        zipFile.close();
        return destDir;
    }
	
	public File createFormSubmissionDirectory(String submissionDir, String formName) throws IOException {
        String dirNameUnique = formName.replaceAll(" ", "_");
        File formSubmissionDir = new File(submissionDir, dirNameUnique);
        if(!formSubmissionDir.exists()) {
        	formSubmissionDir.mkdirs();
        }
        return formSubmissionDir;
    }

    // If overrideFlag is true then it delete the folder if exists.
    public File getInstanceDirectoryByEnvironment(String formFolderName, Environment env, Form form, Instance instance, boolean overrideFlag)
            throws IOException {
        String dirPath = getEnvironmenDirectory(env) + "/instances/" + form.getId();
        if(instance != null) {
            dirPath = dirPath + "/" + instance.getId();
        }

        File dir = new File(dirPath, formFolderName);
        if(overrideFlag) {
            deleteDirectory(dir);
        }
        return dir;
    }
	
	public File getEnvironmenDirectory(Environment env) {
        File envDir = new File(uploadDir, env.getSlug());
        if(!envDir.exists()) {
        	envDir.mkdirs();
        }
        return envDir;
    }

    public File createDirectory(File dir) {
        if(!dir.exists()) {
        	dir.mkdirs();
        }
        return dir;
    }
	
	public boolean deleteDirectory(File dir) throws IOException {
		if(dir.exists()) {
			FileUtils.deleteDirectory(dir);
        }
        return true;
    }
    
    public File getXMLFileFromSubmission(MultiValueMap<String, MultipartFile> filesMap) {
        try {
            for(String paramName : filesMap.keySet()) {
				for(MultipartFile paramFile : filesMap.get(paramName)) {
					String fn = paramFile.getOriginalFilename();
					if (fn.endsWith(".zip")){
                        File zipFile = File.createTempFile(fn, ".zip");
                        FileOutputStream fos = new FileOutputStream(zipFile);
                        fos.write(paramFile.getBytes());
                        fos.close();
                        return zipFile;
					} else if(fn.endsWith(".xml")) {
                        File desFile = File.createTempFile(fn, ".xml");
                        FileOutputStream fos = new FileOutputStream(desFile);
                        fos.write(paramFile.getBytes());
                        fos.close();
                        return desFile;
					}
				}
			}
            
        } catch(Exception e) {
            logger.error("Exception: ", e.getMessage());
        }
        return null;
    }
}
