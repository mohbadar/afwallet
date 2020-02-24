package af.gov.anar.ebreshna.configuration.payment.service;

import af.gov.anar.ebreshna.configuration.payment.model.CounterMaster;
import af.gov.anar.ebreshna.configuration.payment.model.FeePenaltyConfiguration;
import af.gov.anar.ebreshna.configuration.payment.repository.CounterRepository;
import af.gov.anar.ebreshna.configuration.payment.repository.FeePenaltyConfigurationRepository;
import af.gov.anar.ebreshna.infrastructure.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class FeePenaltyService {

    @Autowired
    private FeePenaltyConfigurationRepository repository;

    @Autowired
    private UserService userService;

    public FeePenaltyConfiguration save(FeePenaltyConfiguration obj)
    {
        return repository.save(obj);
    }

    public List<FeePenaltyConfiguration> findall()
    {
        return repository.findAll();
    }

    public FeePenaltyConfiguration findOne(long id)
    {
        return repository.getOne(id);
    }

    public void delete(long id)
    {
        FeePenaltyConfiguration obj = repository.getOne(id);
        obj.setDeleted(true);
        obj.setUserId(userService.getId());
        obj.setDeletedAt(new Date());
        save(obj);
    }
}
