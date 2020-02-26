package af.gov.anar.ebreshna.configuration.billing.bill_due_day;

import af.gov.anar.ebreshna.configuration.billing.bill_due_day.BillDueDay;
import af.gov.anar.ebreshna.configuration.billing.bill_due_day.BillDueDayRepository;
import af.gov.anar.ebreshna.infrastructure.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BillDueDayService {

    @Autowired
    private BillDueDayRepository repository;

    @Autowired
    private UserService userService;

    public BillDueDay save(BillDueDay obj)
    {
        return repository.save(obj);
    }

    public List<BillDueDay> findall()
    {
        return repository.findAll();
    }

    public BillDueDay findOne(long id)
    {
        return repository.getOne(id);
    }

    public void delete(long id)
    {
        BillDueDay obj = repository.getOne(id);
        obj.setDeleted(true);
        obj.setUserId(userService.getId());
        obj.setDeletedAt(new Date());
        save(obj);
    }
}
