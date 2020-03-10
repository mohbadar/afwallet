package af.gov.anar.dck.folder.repository;
import af.gov.anar.dck.folder.model.Folder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface FolderRepository extends JpaRepository<Folder, Long>{

    // @Query("SELECT new af.gov.anar.dck.domain.model.Folder(f.id, f.name, f.type, f.path, f.envSlug) from Folder f WHERE f.path LIKE %:path%")
    // public List<Folder> findByPathLike(@Param("path") String path);
    // public Folder  findById(Long id);
    public List<Folder> findAllByPath(String path);


}