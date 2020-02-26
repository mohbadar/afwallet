package af.gov.anar.ebreshna.configuration.billing.service;

import af.gov.anar.ebreshna.configuration.billing.model.CustomerGroupMaster;
import af.gov.anar.ebreshna.configuration.billing.model.ProcessBehaviourLinkConfiguration;
import af.gov.anar.ebreshna.configuration.billing.repository.CustomerGroupRepository;
import af.gov.anar.ebreshna.configuration.billing.repository.ProcessBehaviourLinkRepository;
import af.gov.anar.ebreshna.infrastructure.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ProcessBehaviourLinkService {

    @Autowired
    private ProcessBehaviourLinkRepository repository;

    @Autowired
    private UserService userService;

    public ProcessBehaviourLinkConfiguration save(ProcessBehaviourLinkConfiguration obj)
    {
        return repository.save(obj);
    }

    public List<ProcessBehaviourLinkConfiguration> findall()
    {
        return repository.findAll();
    }

    public ProcessBehaviourLinkConfiguration findOne(long id)
    {
        return repository.getOne(id);
    }

    public void delete(long id)
    {
        ProcessBehaviourLinkConfiguration obj = repository.getOne(id);
        obj.setDeleted(true);
        obj.setUserId(userService.getId());
        obj.setDeletedAt(new Date());
        save(obj);
    }
}
