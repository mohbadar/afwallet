package af.gov.anar.ebreshna.configuration.office.service;

import af.gov.anar.ebreshna.configuration.office.model.PremisesCategory;
import af.gov.anar.ebreshna.configuration.office.repository.PremisesCategoryRepository;
import af.gov.anar.ebreshna.infrastructure.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PremisesCategoryService {

    @Autowired
    private PremisesCategoryRepository repository;

    @Autowired
    private UserService userService;

    public PremisesCategory save(PremisesCategory obj)
    {
        return repository.save(obj);
    }

    public List<PremisesCategory> findall()
    {
        return repository.findAll();
    }

    public PremisesCategory findOne(long id)
    {
        return repository.getOne(id);
    }

    public void delete(long id)
    {
        PremisesCategory obj = repository.getOne(id);
        obj.setDeleted(true);
        obj.setUserId(userService.getId());
        obj.setDeletedAt(new Date());
        save(obj);
    }
}
