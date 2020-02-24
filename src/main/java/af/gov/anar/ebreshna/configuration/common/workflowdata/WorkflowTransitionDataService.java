package af.gov.anar.ebreshna.configuration.common.workflowdata;

import af.gov.anar.ebreshna.infrastructure.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class WorkflowTransitionDataService {

    @Autowired
    private WorkflowTransitionDataRepository repository;

    @Autowired
    private UserService userService;

    public WorkflowTransitionData save(WorkflowTransitionData obj)
    {
        return repository.save(obj);
    }

    public List<WorkflowTransitionData> findall()
    {
        return repository.findAll();
    }

    public WorkflowTransitionData findOne(long id)
    {
        return repository.getOne(id);
    }

    public void delete(long id)
    {
        WorkflowTransitionData obj = repository.getOne(id);
        obj.setDeleted(true);
        obj.setUserId(userService.getId());
        obj.setDeletedAt(new Date());
        save(obj);
    }
}
