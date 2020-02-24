package af.gov.anar.ebreshna.configuration.nsc.service;


import af.gov.anar.ebreshna.configuration.nsc.model.DocumentCategoryMaster;
import af.gov.anar.ebreshna.configuration.nsc.repository.DocumentCategoryRepository;
import af.gov.anar.ebreshna.infrastructure.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DocumentCategoryService {

    @Autowired
    private DocumentCategoryRepository repository;

    @Autowired
    private UserService userService;

    public DocumentCategoryMaster save(DocumentCategoryMaster obj)
    {
        return repository.save(obj);
    }

    public List<DocumentCategoryMaster> findall()
    {
        return repository.findAll();
    }

    public DocumentCategoryMaster findOne(long id)
    {
        return repository.getOne(id);
    }

    public void delete(long id)
    {
        DocumentCategoryMaster obj = repository.getOne(id);
        obj.setDeleted(true);
        obj.setUserId(userService.getId());
        obj.setDeletedAt(new Date());
        save(obj);
    }
}
