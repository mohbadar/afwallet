package af.gov.anar.ebreshna.nsc.applicant_document;

import af.gov.anar.ebreshna.infrastructure.service.UserService;
import af.gov.anar.ebreshna.nsc.applicant_document.ApplicantDocument;
import af.gov.anar.ebreshna.nsc.applicant_document.ApplicantDocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ApplicantDocumentService {

    @Autowired
    private ApplicantDocumentRepository repository;

    @Autowired
    private UserService userService;

    public ApplicantDocument save(ApplicantDocument obj)
    {
        return repository.save(obj);
    }

    public List<ApplicantDocument> findall()
    {
        return repository.findAll();
    }

    public ApplicantDocument findOne(long id)
    {
        return repository.getOne(id);
    }

    public void delete(long id)
    {
        ApplicantDocument obj = repository.getOne(id);
        obj.setDeleted(true);
        obj.setUserId(userService.getId());
        obj.setDeletedAt(new Date());
        save(obj);
    }

}
