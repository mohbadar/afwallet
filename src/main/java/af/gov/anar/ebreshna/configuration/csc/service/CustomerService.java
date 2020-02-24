package af.gov.anar.ebreshna.configuration.csc.service;

import af.gov.anar.ebreshna.configuration.csc.model.Customer;
import af.gov.anar.ebreshna.configuration.csc.repository.CustomerRepository;
import af.gov.anar.ebreshna.infrastructure.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository repository;

    @Autowired
    private UserService userService;

    public Customer save(Customer obj)
    {
        return repository.save(obj);
    }

    public List<Customer> findall()
    {
        return repository.findAll();
    }

    public Customer findOne(long id)
    {
        return repository.getOne(id);
    }

    public void delete(long id)
    {
        Customer obj = repository.getOne(id);
        obj.setDeleted(true);
        obj.setUserId(userService.getId());
        obj.setDeletedAt(new Date());
        save(obj);
    }


}