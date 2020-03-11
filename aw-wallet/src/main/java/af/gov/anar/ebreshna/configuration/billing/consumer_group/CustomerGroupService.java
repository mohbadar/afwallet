package af.gov.anar.ebreshna.configuration.billing.consumer_group;

import af.gov.anar.ebreshna.infrastructure.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CustomerGroupService {

    @Autowired
    private CustomerGroupRepository repository;

    @Autowired
    private UserService userService;

    public CustomerGroupMaster save(CustomerGroupMaster obj)
    {
        return repository.save(obj);
    }

    public List<CustomerGroupMaster> findall()
    {
        return repository.findAll();
    }

    public CustomerGroupMaster findOne(long id)
    {
        return repository.getOne(id);
    }

    public void delete(long id)
    {
        CustomerGroupMaster obj = repository.getOne(id);
        obj.setDeleted(true);
        obj.setUserId(userService.getId());
        obj.setDeletedAt(new Date());
        save(obj);
    }
}
