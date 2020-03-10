package af.gov.anar.dck.folder.service;


import af.gov.anar.dck.common.auth.UserAuthService;
import af.gov.anar.dck.folder.model.Folder;
import af.gov.anar.dck.folder.repository.FolderRepository;
import af.gov.anar.dck.folder.service.FolderService;
import af.gov.anar.dck.infrastructure.util.DateTimeUtil;
import af.gov.anar.dck.useradministration.service.UserService;
import af.gov.anar.dck.form.service.FormServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * FolderServiceImpl
 *
 * @return returns newly created file/folder with it's siblings
 * 
 * @throws IOException       If an I/O error occurred
 *
 * @throws SecurityException If a security manager exists and its method denies
 *                           write access to the file *
 * @author jalil haidari
 */

@Service
public class FolderServiceImpl implements FolderService {
    Logger logger = LoggerFactory.getLogger(FormServiceImpl.class);
    @Value("${app.upload.dir}")
    private String uploadDir;
    @Autowired
    public UserAuthService userAuthService;
    @Autowired
    public UserService userService;
    @Autowired
    public FolderRepository folderRepository;
    @Autowired
    private DateTimeUtil dateTimeUtil;

    @Override
    public List<Folder> getRootFolder() {

        // return new Folder(1L, "/"+userAuthService.getCurrentEnv(), ".jpg",
        // "/"+userAuthService.getCurrentEnv()+"/filename.jps");
        // return folderRepository.findByPathLike(path);
        String path = "/" + userAuthService.getCurrentEnv().toUpperCase();
        List<Folder> roots = new ArrayList<Folder>();
        roots = folderRepository.findAllByPath(path);
        if(roots.size()<1){
            Folder rootFolder = new Folder();
            rootFolder.setEnvSlug(userAuthService.getCurrentEnv().toUpperCase());
            rootFolder.setPath(path);
            rootFolder.setDirectory(true);
            rootFolder.setName(userAuthService.getCurrentEnv().toUpperCase());
            rootFolder.setType("fOLDER");
            rootFolder.setCreatedAt(dateTimeUtil.getCurrentDate());
            rootFolder.setUpdatedAt(dateTimeUtil.getCurrentDate());
            Folder f = this.folderRepository.saveAndFlush(rootFolder);
            roots.add(f);

        }
        return roots;
    }

    @Override
    public List<Folder> findAllSubFolders(Long folderId) {

        Folder f = folderRepository.findById(folderId).get();
        String path = f.getPath();
        String finalPath = path + "/" + f.getName();
        return folderRepository.findAllByPath(finalPath);
    }

    @Override
    public List<Folder> createNewFolder(String folderName, Long parentId) {
        Folder newFolder = new Folder();
        newFolder.setEnvSlug(userAuthService.getCurrentEnv());
        Folder parentFolder = folderRepository.findById(parentId).get();
        String path = parentFolder.getPath() + "/" + parentFolder.getName();

        newFolder.setPath(path);
        newFolder.setName(folderName);
        newFolder.setType("FOLDER");
        newFolder.setDirectory(true);
        newFolder.setCreatedAt(this.dateTimeUtil.getCurrentDate());
        newFolder.setCreatedAt(this.dateTimeUtil.getCurrentDate());
        Folder f = new Folder();
        try {
            f = folderRepository.saveAndFlush(newFolder);
            System.out.println("newly created folder is:" + f.toString());

        } catch (Exception e) {
            System.out.println(" Exception >>>>>" + e.toString());
        }
        return this.findAllSubFolders(parentId);
    }

    /**
     * upload file
     *
     * @return returns newly created file/folder with it's siblings
     * 
     * @throws Exception       If an I/O error occurred
     *
     * @throws SecurityException If a security manager exists and its method denies
     *                           write access to the file *
     * 
     */

    @Override
    public List<Folder> uploadFile(Long parentId, MultipartFile file) {
        Folder parentFolder = folderRepository.findById(parentId).get();
        String filePath = parentFolder.getPath() + "/" + parentFolder.getName();
        String suffix = file.getOriginalFilename().split("\\.")[1];
        File directory = new File(uploadDir + "/" + filePath);
        if (!directory.exists()) {
            directory.mkdir();
        }
        try {
            File f = new File(directory.getAbsolutePath() + "/" + file.getOriginalFilename());
            if (!f.exists()) {
                f.createNewFile();
            }

            FileOutputStream fout = new FileOutputStream(f);
            fout.write(file.getBytes());
            fout.close();
            Folder newFolder = new Folder();
            newFolder.setPath(filePath);
            newFolder.setEnvSlug(userAuthService.getCurrentEnv());
            newFolder.setName(file.getOriginalFilename().toString());
            newFolder.setType(suffix.toString());
            newFolder.setDirectory(false);
            newFolder.setCreatedAt(this.dateTimeUtil.getCurrentDate());
            newFolder.setUpdatedAt(this.dateTimeUtil.getCurrentDate());
            this.folderRepository.saveAndFlush(newFolder);
            // FileWriter fw = new FileWriter(f.getAbsoluteFile());
            // BufferedWriter bw = new BufferedWriter(fw);
            // bw.write(value);
            // bw.close();

        } catch (Exception e) {
            System.out.println(">>>> exception " + e.toString());
        }

        return this.findAllSubFolders(parentId);
    }

    /**
     * return the file as Resource
     *
     * @return a file by its id
     * 
     * @throws IOException If an I/O error occurred
     *
     * 
     * 
     */
    @Override
    public File findFile(Long id) throws Exception {
        Folder f = this.folderRepository.findById(id).get();
        File file = new File("");

        // Resource resource;
        try {
            String absolutePath = this.uploadDir+f.getPath() + "/" + f.getName();
            System.out.println("the absolutePath is:"+ absolutePath);
            File directory = new File(absolutePath);
            file = new File(directory.getAbsolutePath());
            return file;
            // Path path = file.toPath();
            // resource = new UrlResource(path.toUri());
            // if (resource.exists() || resource.isReadable()) {
            // return resource;
            // }

        } catch (Exception e) {
            System.out.println(">>> Exception:" + e.toString());

        }
        return file;

    }

}