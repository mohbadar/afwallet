package af.gov.anar.ebreshna.configuration.common.fee.service;

import af.gov.anar.ebreshna.configuration.common.fee.model.FeeType;
import af.gov.anar.ebreshna.configuration.common.fee.repository.FeeTypeRepository;
import af.gov.anar.ebreshna.configuration.common.workflowdata.WorkflowTransitionData;
import af.gov.anar.ebreshna.configuration.common.workflowdata.WorkflowTransitionDataRepository;
import af.gov.anar.ebreshna.infrastructure.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class FeeTypeService {

    @Autowired
    private FeeTypeRepository repository;

    @Autowired
    private UserService userService;

    public FeeType save(FeeType obj)
    {
        return repository.save(obj);
    }

    public List<FeeType> findall()
    {
        return repository.findAll();
    }

    public FeeType findOne(long id)
    {
        return repository.getOne(id);
    }

    public void delete(long id)
    {
        FeeType obj = repository.getOne(id);
        obj.setDeleted(true);
        obj.setUserId(userService.getId());
        obj.setDeletedAt(new Date());
        save(obj);
    }
}
