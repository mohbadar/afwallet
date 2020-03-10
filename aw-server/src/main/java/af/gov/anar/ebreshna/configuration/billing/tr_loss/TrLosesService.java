package af.gov.anar.ebreshna.configuration.billing.tr_loss;

import af.gov.anar.ebreshna.configuration.billing.tr_loss.TrLossesConfiguration;
import af.gov.anar.ebreshna.configuration.billing.tr_loss.TrLosesRepository;
import af.gov.anar.ebreshna.infrastructure.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TrLosesService {

    @Autowired
    private TrLosesRepository repository;

    @Autowired
    private UserService userService;

    public TrLossesConfiguration save(TrLossesConfiguration obj)
    {
        return repository.save(obj);
    }

    public List<TrLossesConfiguration> findall()
    {
        return repository.findAll();
    }

    public TrLossesConfiguration findOne(long id)
    {
        return repository.getOne(id);
    }

    public void delete(long id)
    {
        TrLossesConfiguration obj = repository.getOne(id);
        obj.setDeleted(true);
        obj.setUserId(userService.getId());
        obj.setDeletedAt(new Date());
        save(obj);
    }
}
