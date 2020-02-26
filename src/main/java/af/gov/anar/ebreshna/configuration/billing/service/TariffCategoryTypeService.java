package af.gov.anar.ebreshna.configuration.billing.service;

import af.gov.anar.ebreshna.configuration.billing.model.CustomerGroupMaster;
import af.gov.anar.ebreshna.configuration.billing.model.TariffCategoryTypeMaster;
import af.gov.anar.ebreshna.configuration.billing.repository.CustomerGroupRepository;
import af.gov.anar.ebreshna.configuration.billing.repository.TariffCategoryTypeRepository;
import af.gov.anar.ebreshna.configuration.common.tariff.model.TariffCategory;
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
