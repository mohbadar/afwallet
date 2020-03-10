package af.gov.anar.dck.folder.api;

import af.gov.anar.dck.folder.model.Folder;
import af.gov.anar.dck.folder.service.FolderService;
import af.gov.anar.dck.infrastructure.logger.Loggable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.util.List;

// import java.io.File;
// import java.io.FileInputStream;
// import java.io.FileOutputStream;
// import java.io.IOException;
// import java.util.ArrayList;
// import java.util.Collection;
// import java.io.OutputStream;
// import java.nio.file.Files;
// import java.nio.file.Path;
// import javax.validation.Valid;
// import javax.xml.parsers.ParserConfigurationException;
// import javax.servlet.http.HttpServletRequest;
// import javax.servlet.http.HttpServletResponse;
// import org.apache.poi.ss.usermodel.Cell;
// import org.apache.poi.ss.usermodel.Row;
// import org.apache.poi.xssf.usermodel.XSSFSheet;
// import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.RequestMethod;
// import org.xml.sax.SAXException;
// import com.fasterxml.jackson.databind.JsonNode;
// import com.fasterxml.jackson.databind.ObjectMapper;
// import com.fasterxml.jackson.databind.node.ArrayNode;
// import com.fasterxml.jackson.databind.node.ObjectNode;
// import com.google.gson.Gson;
// import af.gov.anar.dck.form.model.Form;
// import af.gov.anar.dck.useradministration.model.Group;
// import af.gov.anar.dck.instance.model.Instance;
// import af.gov.anar.dck.report.model.Report;
// import af.gov.anar.dck.useradministration.model.User;
// import af.gov.anar.dck.workflow.model.Workflow;
// import af.gov.anar.dck.domain.service.FormService;
// import af.gov.anar.dck.domain.service.GroupService;
// import af.gov.anar.dck.domain.service.WorkflowService;
// import af.gov.anar.dck.domain.util.DateTimeUtil;
// import af.gov.anar.dck.domain.service.InstanceService;
// import af.gov.anar.dck.domain.service.UserService;
// import af.gov.anar.dck.domain.auth.FormAuthService;
// import af.gov.anar.dck.domain.auth.ReportAuthService;
// import af.gov.anar.dck.domain.auth.UserAuthService;
// import af.gov.anar.dck.domain.auth.InstanceAuthService;

@RestController
@RequestMapping({ "/api/folders" })
public class FolderController{

    Logger logger = LoggerFactory.getLogger(FolderController.class);

    @Autowired
    private FolderService folderService;
    // @Autowired
    // private GroupService groupService;
    // @Autowired
    // private UserService userService;

    // @Loggable
    // @GetMapping
    // public List findAll() {
    // // return formService.findAll();
    // return formAuthService.findAllWithoutXMLContent();
    // }

    @Loggable
    @GetMapping
    public List<Folder> findRoot() {
        // String path = "/"+userService.getCurrentEnv().toUpperCase();

        List<Folder> fs = folderService.getRootFolder();
        System.out.println("the returned folder:" + fs.toString());
        return fs;

    }

    // @Loggable
    // @GetMapping(path = { "/{formId}" })
    // public List findAllInstancesOfForm(@PathVariable("formId") Long formId) {
    // return InstanceAuthService.findAllByForm(formId);
    // }
    @Loggable
    @GetMapping(path = { "/{folderId}" })
    public List<Folder> findAllSubFolders(@PathVariable("folderId") Long folderId) {
        // return InstanceAuthService.findAllByForm(folderId);
        // return folderService
        return folderService.findAllSubFolders(folderId);
    }

    @Loggable
    @PostMapping(path = { "/{parentId}" })
    public List<Folder> createNewFolder(@PathVariable("parentId") Long parentId, @RequestBody String folderName) {
        return this.folderService.createNewFolder(folderName, parentId);

    }

    @Loggable
    @PostMapping(path = { "/file/{parentId}" }, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public List<Folder> uploadNewFile(@PathVariable("parentId") Long parentId,
            @RequestParam("file") MultipartFile multipart) {
        return this.folderService.uploadFile(parentId, multipart);
    }

    @Loggable
    @GetMapping(path = { "/file/{fileId}" })
    public ResponseEntity<Resource> getFile(@PathVariable("fileId") Long fileId, HttpServletRequest request) throws Exception {
        File file = this.folderService.findFile(fileId);
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
        HttpHeaders headers = new HttpHeaders();
        String contentType = null;
        try{
            contentType = request.getServletContext().getMimeType(file.getAbsolutePath());
        }catch(Exception e){
            System.out.println("Exception:>>>"+e.toString());
        }
        // Fallback to the default content type if type could not be determined
        if(contentType == null){
            contentType = "application/octet-stream";
        }
        headers.add("Content-Disposition", "attachment; filename=" + file.getName());

        return ResponseEntity.ok()
        .headers(headers)
        .contentType(MediaType.parseMediaType("application/octet-stream"))
        .contentLength(file.length()).body(resource);

    }

}