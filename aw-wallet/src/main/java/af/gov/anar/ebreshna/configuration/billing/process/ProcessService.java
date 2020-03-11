package af.gov.anar.ebreshna.configuration.billing.process;

import af.gov.anar.ebreshna.configuration.billing.process.ProcessConfiguration;
import af.gov.anar.ebreshna.configuration.billing.process.ProcessRepository;
import af.gov.anar.ebreshna.infrastructure.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ProcessService {

    @Autowired
    private ProcessRepository repository;

    @Autowired
    private UserService userService;

    public ProcessConfiguration save(ProcessConfiguration obj)
    {
        return repository.save(obj);
    }

    public List<ProcessConfiguration> findall()
    {
        return repository.findAll();
    }

    public ProcessConfiguration findOne(long id)
    {
        return repository.getOne(id);
    }

    public void delete(long id)
    {
        ProcessConfiguration obj = repository.getOne(id);
        obj.setDeleted(true);
        obj.setUserId(userService.getId());
        obj.setDeletedAt(new Date());
        save(obj);
    }

}
