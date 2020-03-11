package af.gov.anar.ebreshna.configuration.office.premises_sub_category;

import af.gov.anar.ebreshna.configuration.office.premises_sub_category.PremisesSubCategory;
import af.gov.anar.ebreshna.configuration.office.premises_sub_category.PremisesSubCategoryRepository;
import af.gov.anar.ebreshna.infrastructure.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PremisesSubCategoryService {

    @Autowired
    private PremisesSubCategoryRepository repository;

    @Autowired
    private UserService userService;

    public PremisesSubCategory save(PremisesSubCategory obj)
    {
        return repository.save(obj);
    }

    public List<PremisesSubCategory> findAll()
    {
        return repository.findAll();
    }

    public PremisesSubCategory findOne(long id)
    {
        return repository.getOne(id);
    }

    public void delete(long id)
    {
        PremisesSubCategory obj = repository.getOne(id);
        obj.setDeleted(true);
        obj.setUserId(userService.getId());
        obj.setDeletedAt(new Date());
        save(obj);
    }
}
