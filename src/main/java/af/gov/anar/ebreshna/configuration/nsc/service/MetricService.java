package af.gov.anar.ebreshna.configuration.nsc.service;

import af.gov.anar.ebreshna.configuration.nsc.model.MetricMaster;
import af.gov.anar.ebreshna.configuration.nsc.repository.MetricRepository;
import af.gov.anar.ebreshna.infrastructure.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MetricService {

    @Autowired
    private MetricRepository repository;

    @Autowired
    private UserService userService;

    public MetricMaster save(MetricMaster obj)
    {
        return repository.save(obj);
    }

    public List<MetricMaster> findall()
    {
        return repository.findAll();
    }

    public MetricMaster findOne(long id)
    {
        return repository.getOne(id);
    }

    public void delete(long id)
    {
        MetricMaster obj = repository.getOne(id);
        obj.setDeleted(true);
        obj.setUserId(userService.getId());
        obj.setDeletedAt(new Date());
        save(obj);
    }
}
