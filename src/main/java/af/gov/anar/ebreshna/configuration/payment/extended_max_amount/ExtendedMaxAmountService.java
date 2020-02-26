package af.gov.anar.ebreshna.configuration.payment.extended_max_amount;

import af.gov.anar.ebreshna.configuration.payment.extended_max_amount.ExtendedMaxAmount;
import af.gov.anar.ebreshna.configuration.payment.extended_max_amount.ExtendedMaxAmountRepository;
import af.gov.anar.ebreshna.infrastructure.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ExtendedMaxAmountService {

    @Autowired
    private ExtendedMaxAmountRepository repository;

    @Autowired
    private UserService userService;

    public ExtendedMaxAmount save(ExtendedMaxAmount obj)
    {
        return repository.save(obj);
    }

    public List<ExtendedMaxAmount> findall()
    {
        return repository.findAll();
    }

    public ExtendedMaxAmount findOne(long id)
    {
        return repository.getOne(id);
    }

    public void delete(long id)
    {
        ExtendedMaxAmount obj = repository.getOne(id);
        obj.setDeleted(true);
        obj.setUserId(userService.getId());
        obj.setDeletedAt(new Date());
        save(obj);
    }
}
