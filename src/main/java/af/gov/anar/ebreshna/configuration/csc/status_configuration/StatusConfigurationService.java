package af.gov.anar.ebreshna.configuration.csc.status_configuration;

import af.gov.anar.ebreshna.configuration.csc.status_configuration.StatusConfiguration;
import af.gov.anar.ebreshna.configuration.csc.status_configuration.StatusConfigurationRepository;
import af.gov.anar.ebreshna.infrastructure.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class StatusConfigurationService {

    @Autowired
    private StatusConfigurationRepository repository;

    @Autowired
    private UserService userService;

    public StatusConfiguration save(StatusConfiguration obj)
    {
        return repository.save(obj);
    }

    public List<StatusConfiguration> findall()
    {
        return repository.findAll();
    }

    public StatusConfiguration findOne(long id)
    {
        return repository.getOne(id);
    }

    public void delete(long id)
    {
        StatusConfiguration obj = repository.getOne(id);
        obj.setDeleted(true);
        obj.setUserId(userService.getId());
        obj.setDeletedAt(new Date());
        save(obj);
    }
}