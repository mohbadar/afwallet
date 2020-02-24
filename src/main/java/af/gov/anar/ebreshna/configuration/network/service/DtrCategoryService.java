package af.gov.anar.ebreshna.configuration.network.service;

import af.gov.anar.ebreshna.configuration.network.model.DtrCategory;
import af.gov.anar.ebreshna.configuration.network.repository.DtrCategoryRepository;
import af.gov.anar.ebreshna.infrastructure.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DtrCategoryService {

    @Autowired
    private DtrCategoryRepository repository;

    @Autowired
    private UserService userService;

    public DtrCategory save(DtrCategory obj)
    {
        return repository.save(obj);
    }

    public List<DtrCategory> findall()
    {
        return repository.findAll();
    }

    public DtrCategory findOne(long id)
    {
        return repository.getOne(id);
    }

    public void delete(long id)
    {
        DtrCategory obj = repository.getOne(id);
        obj.setDeleted(true);
        obj.setUserId(userService.getId());
        obj.setDeletedAt(new Date());
        save(obj);
    }
}
