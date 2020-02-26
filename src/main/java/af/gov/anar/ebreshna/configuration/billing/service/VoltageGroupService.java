package af.gov.anar.ebreshna.configuration.billing.service;

import af.gov.anar.ebreshna.configuration.billing.enumeration.VoltageGroup;
import af.gov.anar.ebreshna.configuration.billing.model.CustomerGroupMaster;
import af.gov.anar.ebreshna.configuration.billing.model.VoltageGroupMaster;
import af.gov.anar.ebreshna.configuration.billing.repository.CustomerGroupRepository;
import af.gov.anar.ebreshna.configuration.billing.repository.VoltageGroupRepository;
import af.gov.anar.ebreshna.infrastructure.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class VoltageGroupService {

    @Autowired
    private VoltageGroupRepository repository;

    @Autowired
    private UserService userService;

    public VoltageGroupMaster save(VoltageGroupMaster obj)
    {
        return repository.save(obj);
    }

    public List<VoltageGroupMaster> findall()
    {
        return repository.findAll();
    }

    public VoltageGroupMaster findOne(long id)
    {
        return repository.getOne(id);
    }

    public void delete(long id)
    {
        VoltageGroupMaster obj = repository.getOne(id);
        obj.setDeleted(true);
        obj.setUserId(userService.getId());
        obj.setDeletedAt(new Date());
        save(obj);
    }
}
