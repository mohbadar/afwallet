package af.gov.anar.ebreshna.configuration.payment.thirdparty_payment;

import af.gov.anar.ebreshna.configuration.payment.thirdparty_payment.ThirdPartyPayment;
import af.gov.anar.ebreshna.configuration.payment.thirdparty_payment.ThirdPartyPaymentRepository;
import af.gov.anar.ebreshna.infrastructure.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ThirdPartyPaymentService {

    @Autowired
    private ThirdPartyPaymentRepository repository;

    @Autowired
    private UserService userService;

    public ThirdPartyPayment save(ThirdPartyPayment obj)
    {
        return repository.save(obj);
    }

    public List<ThirdPartyPayment> findall()
    {
        return repository.findAll();
    }

    public ThirdPartyPayment findOne(long id)
    {
        return repository.getOne(id);
    }

    public void delete(long id)
    {
        ThirdPartyPayment obj = repository.getOne(id);
        obj.setDeleted(true);
        obj.setUserId(userService.getId());
        obj.setDeletedAt(new Date());
        save(obj);
    }
}
