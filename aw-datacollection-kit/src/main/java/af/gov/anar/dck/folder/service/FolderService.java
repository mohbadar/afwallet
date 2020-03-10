package af.gov.anar.dck.folder.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import af.gov.anar.dck.folder.model.Folder;

import java.io.File;
import java.util.List;

@Service
public interface FolderService {
    public List<Folder> getRootFolder();

    public List<Folder> findAllSubFolders(Long folderId);

    public List<Folder> createNewFolder(String name, Long parentId);

    public List<Folder> uploadFile(Long parentId, MultipartFile mf);
   
    public File findFile(Long id) throws Exception;

}