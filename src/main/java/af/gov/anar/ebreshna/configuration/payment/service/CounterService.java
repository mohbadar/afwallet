package af.gov.anar.ebreshna.configuration.payment.service;

import af.gov.anar.ebreshna.configuration.office.model.FieldStaff;
import af.gov.anar.ebreshna.configuration.office.repository.FieldStaffRepository;
import af.gov.anar.ebreshna.configuration.payment.model.CounterMaster;
import af.gov.anar.ebreshna.configuration.payment.repository.CounterRepository;
import af.gov.anar.ebreshna.infrastructure.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.css.Counter;

import java.util.Date;
import java.util.List;

@Service
public class CounterService {

    @Autowired
    private CounterRepository repository;

    @Autowired
    private UserService userService;

    public CounterMaster save(CounterMaster obj)
    {
        return repository.save(obj);
    }

    public List<CounterMaster> findall()
    {
        return repository.findAll();
    }

    public CounterMaster findOne(long id)
    {
        return repository.getOne(id);
    }

    public void delete(long id)
    {
        CounterMaster obj = repository.getOne(id);
        obj.setDeleted(true);
        obj.setUserId(userService.getId());
        obj.setDeletedAt(new Date());
        save(obj);
    }
}
