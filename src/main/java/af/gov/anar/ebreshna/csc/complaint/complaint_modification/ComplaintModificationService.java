package af.gov.anar.ebreshna.csc.complaint.complaint_modification;

import af.gov.anar.ebreshna.configuration.billing.behaviour.BehaviourConfiguration;
import af.gov.anar.ebreshna.configuration.billing.behaviour.BehaviourRepository;
import af.gov.anar.ebreshna.helpdesk.model.Complaint;
import af.gov.anar.ebreshna.infrastructure.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ComplaintModificationService {

    @Autowired
    private ComplaintModificationRepository repository;

    @Autowired
    private UserService userService;

    public ComplaintModification save(ComplaintModification obj)
    {
        return repository.save(obj);
    }

    public List<ComplaintModification> findall()
    {
        return repository.findAll();
    }

    public ComplaintModification findOne(long id)
    {
        return repository.getOne(id);
    }

    public void delete(long id)
    {
        ComplaintModification obj = repository.getOne(id);
        obj.setDeleted(true);
        obj.setUserId(userService.getId());
        obj.setDeletedAt(new Date());
        save(obj);
    }
}
