package af.gov.anar.ebreshna.configuration.payment.service;

import af.gov.anar.ebreshna.configuration.payment.model.CounterMaster;
import af.gov.anar.ebreshna.configuration.payment.model.PostingDaysConfiguration;
import af.gov.anar.ebreshna.configuration.payment.repository.CounterRepository;
import af.gov.anar.ebreshna.configuration.payment.repository.PostingDaysConfigurationRepository;
import af.gov.anar.ebreshna.infrastructure.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PostingDaysConfigurationService {

    @Autowired
    private PostingDaysConfigurationRepository repository;

    @Autowired
    private UserService userService;

    public PostingDaysConfiguration save(PostingDaysConfiguration obj)
    {
        return repository.save(obj);
    }

    public List<PostingDaysConfiguration> findall()
    {
        return repository.findAll();
    }

    public PostingDaysConfiguration findOne(long id)
    {
        return repository.getOne(id);
    }

    public void delete(long id)
    {
        PostingDaysConfiguration obj = repository.getOne(id);
        obj.setDeleted(true);
        obj.setUserId(userService.getId());
        obj.setDeletedAt(new Date());
        save(obj);
    }

}