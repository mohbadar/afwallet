package af.gov.anar.ebreshna.csc.complaint.complaint_request_type;

import af.gov.anar.ebreshna.configuration.billing.behaviour.BehaviourConfiguration;
import af.gov.anar.ebreshna.configuration.billing.behaviour.BehaviourRepository;
import af.gov.anar.ebreshna.csc.complaint.complaint_request.ComplaintRequest;
import af.gov.anar.ebreshna.infrastructure.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ComplaintRequestTypeService {

    @Autowired
    private ComplaintRequestTypeRepository repository;

    @Autowired
    private UserService userService;

    public ComplaintRequestType save(ComplaintRequestType obj)
    {
        return repository.save(obj);
    }

    public List<ComplaintRequestType> findall()
    {
        return repository.findAll();
    }

    public ComplaintRequestType findOne(long id)
    {
        return repository.getOne(id);
    }

    public void delete(long id)
    {
        ComplaintRequestType obj = repository.getOne(id);
        obj.setDeleted(true);
        obj.setUserId(userService.getId());
        obj.setDeletedAt(new Date());
        save(obj);
    }
}
