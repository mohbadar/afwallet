package af.gov.anar.ebreshna.configuration.billing.tariff_category_type;

import af.gov.anar.ebreshna.configuration.billing.tariff_category_type.TariffCategoryTypeMaster;
import af.gov.anar.ebreshna.configuration.billing.tariff_category_type.TariffCategoryTypeRepository;
import af.gov.anar.ebreshna.infrastructure.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TariffCategoryTypeService {

    @Autowired
    private TariffCategoryTypeRepository repository;

    @Autowired
    private UserService userService;

    public TariffCategoryTypeMaster save(TariffCategoryTypeMaster obj)
    {
        return repository.save(obj);
    }

    public List<TariffCategoryTypeMaster> findall()
    {
        return repository.findAll();
    }

    public TariffCategoryTypeMaster findOne(long id)
    {
        return repository.getOne(id);
    }

    public void delete(long id)
    {
        TariffCategoryTypeMaster obj = repository.getOne(id);
        obj.setDeleted(true);
        obj.setUserId(userService.getId());
        obj.setDeletedAt(new Date());
        save(obj);
    }
}
