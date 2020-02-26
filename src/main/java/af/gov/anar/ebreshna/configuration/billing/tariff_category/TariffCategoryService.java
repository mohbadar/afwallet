package af.gov.anar.ebreshna.configuration.billing.tariff_category;

import af.gov.anar.ebreshna.configuration.billing.tariff_category.TariffCategoryMaster;
import af.gov.anar.ebreshna.configuration.billing.tariff_category.TariffCategoryRepository;
import af.gov.anar.ebreshna.infrastructure.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TariffCategoryService {

    @Autowired
    private TariffCategoryRepository repository;

    @Autowired
    private UserService userService;

    public TariffCategoryMaster save(TariffCategoryMaster obj)
    {
        return repository.save(obj);
    }

    public List<TariffCategoryMaster> findall()
    {
        return repository.findAll();
    }

    public TariffCategoryMaster findOne(long id)
    {
        return repository.getOne(id);
    }

    public void delete(long id)
    {
        TariffCategoryMaster obj = repository.getOne(id);
        obj.setDeleted(true);
        obj.setUserId(userService.getId());
        obj.setDeletedAt(new Date());
        save(obj);
    }
}
