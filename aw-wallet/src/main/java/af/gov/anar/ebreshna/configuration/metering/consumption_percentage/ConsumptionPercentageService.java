package af.gov.anar.ebreshna.configuration.metering.consumption_percentage;

import af.gov.anar.ebreshna.configuration.metering.consumption_percentage.ConsumptionPercentage;
import af.gov.anar.ebreshna.configuration.metering.consumption_percentage.ConsumptionPercentageRepository;
import af.gov.anar.ebreshna.infrastructure.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ConsumptionPercentageService {

    @Autowired
    private ConsumptionPercentageRepository repository;

    @Autowired
    private UserService userService;

    public ConsumptionPercentage save(ConsumptionPercentage obj)
    {
        return repository.save(obj);
    }

    public List<ConsumptionPercentage> findall()
    {
        return repository.findAll();
    }

    public ConsumptionPercentage findOne(long id)
    {
        return repository.getOne(id);
    }

    public void delete(long id)
    {
        ConsumptionPercentage obj = repository.getOne(id);
        obj.setDeleted(true);
        obj.setUserId(userService.getId());
        obj.setDeletedAt(new Date());
        save(obj);
    }
}
