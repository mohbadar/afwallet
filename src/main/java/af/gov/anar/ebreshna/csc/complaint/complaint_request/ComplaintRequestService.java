package af.gov.anar.ebreshna.csc.complaint.complaint_request;

import af.gov.anar.ebreshna.configuration.billing.behaviour.BehaviourConfiguration;
import af.gov.anar.ebreshna.configuration.billing.behaviour.BehaviourRepository;
import af.gov.anar.ebreshna.helpdesk.model.Complaint;
import af.gov.anar.ebreshna.infrastructure.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ComplaintRequestService {

    @Autowired
    private ComplaintRequestRepository repository;

    @Autowired
    private UserService userService;

    public ComplaintRequest save(ComplaintRequest obj)
    {
        return repository.save(obj);
    }

    public List<ComplaintRequest> findall()
    {
        return repository.findAll();
    }

    public ComplaintRequest findOne(long id)
    {
        return repository.getOne(id);
    }

    public void delete(long id)
    {
        ComplaintRequest obj = repository.getOne(id);
        obj.setDeleted(true);
        obj.setUserId(userService.getId());
        obj.setDeletedAt(new Date());
        save(obj);
    }
}
