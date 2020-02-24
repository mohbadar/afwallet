package af.gov.anar.ebreshna.configuration.nsc.service;

import af.gov.anar.ebreshna.configuration.nsc.model.DocumentMaster;
import af.gov.anar.ebreshna.configuration.nsc.repository.DocumentRepository;
import af.gov.anar.ebreshna.infrastructure.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DocumentService {

    @Autowired
    private DocumentRepository repository;

    @Autowired
    private UserService userService;

    public DocumentMaster save(DocumentMaster obj)
    {
        return repository.save(obj);
    }

    public List<DocumentMaster> findall()
    {
        return repository.findAll();
    }

    public DocumentMaster findOne(long id)
    {
        return repository.getOne(id);
    }

    public void delete(long id)
    {
        DocumentMaster obj = repository.getOne(id);
        obj.setDeleted(true);
        obj.setUserId(userService.getId());
        obj.setDeletedAt(new Date());
        save(obj);
    }
}
