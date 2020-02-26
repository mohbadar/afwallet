package af.gov.anar.ebreshna.configuration.payment.payment_mode;

import af.gov.anar.ebreshna.configuration.payment.payment_mode.PaymentModeDetail;
import af.gov.anar.ebreshna.configuration.payment.payment_mode.PaymentModeDetailRepository;
import af.gov.anar.ebreshna.infrastructure.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PaymentModeDetailService {

    @Autowired
    private PaymentModeDetailRepository repository;

    @Autowired
    private UserService userService;

    public PaymentModeDetail save(PaymentModeDetail obj)
    {
        return repository.save(obj);
    }

    public List<PaymentModeDetail> findall()
    {
        return repository.findAll();
    }

    public PaymentModeDetail findOne(long id)
    {
        return repository.getOne(id);
    }

    public void delete(long id)
    {
        PaymentModeDetail obj = repository.getOne(id);
        obj.setDeleted(true);
        obj.setUserId(userService.getId());
        obj.setDeletedAt(new Date());
        save(obj);
    }

}
