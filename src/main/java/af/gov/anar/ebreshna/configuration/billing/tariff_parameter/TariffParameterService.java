package af.gov.anar.ebreshna.configuration.billing.tariff_parameter;

import af.gov.anar.ebreshna.configuration.billing.tariff_parameter.TariffParameter;
import af.gov.anar.ebreshna.configuration.billing.tariff_parameter.TariffParameterRepository;
import af.gov.anar.ebreshna.infrastructure.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TariffParameterService {

    @Autowired
    private TariffParameterRepository repository;

    @Autowired
    private UserService userService;

    public TariffParameter save(TariffParameter obj)
    {
        return repository.save(obj);
    }

    public List<TariffParameter> findall()
    {
        return repository.findAll();
    }

    public TariffParameter findOne(long id)
    {
        return repository.getOne(id);
    }

    public void delete(long id)
    {
        TariffParameter obj = repository.getOne(id);
        obj.setDeleted(true);
        obj.setUserId(userService.getId());
        obj.setDeletedAt(new Date());
        save(obj);
    }
}
