package af.gov.anar.ebreshna.configuration.payment.service;

import af.gov.anar.ebreshna.configuration.office.model.FieldStaff;
import af.gov.anar.ebreshna.configuration.office.repository.FieldStaffRepository;
import af.gov.anar.ebreshna.configuration.payment.model.BankMaster;
import af.gov.anar.ebreshna.configuration.payment.repository.BankRepository;
import af.gov.anar.ebreshna.infrastructure.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BankService {

    @Autowired
    private BankRepository repository;

    @Autowired
    private UserService userService;

    public BankMaster save(BankMaster obj)
    {
        return repository.save(obj);
    }

    public List<BankMaster> findall()
    {
        return repository.findAll();
    }

    public BankMaster findOne(long id)
    {
        return repository.getOne(id);
    }

    public void delete(long id)
    {
        BankMaster obj = repository.getOne(id);
        obj.setDeleted(true);
        obj.setUserId(userService.getId());
        obj.setDeletedAt(new Date());
        save(obj);
    }
}
