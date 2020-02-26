package af.gov.anar.ebreshna.configuration.office.office_type;

import af.gov.anar.ebreshna.configuration.office.office_type.OfficeType;
import af.gov.anar.ebreshna.configuration.office.office_type.OfficeTypeRepository;
import af.gov.anar.ebreshna.infrastructure.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OfficeTypeService {

    @Autowired
    private OfficeTypeRepository repository;

    @Autowired
    private UserService userService;

    public OfficeType save(OfficeType obj)
    {
        return repository.save(obj);
    }

    public List<OfficeType> findall()
    {
        return repository.findAll();
    }

    public OfficeType findOne(long id)
    {
        return repository.getOne(id);
    }

    public void delete(long id)
    {
        OfficeType obj = repository.getOne(id);
        obj.setDeleted(true);
        obj.setUserId(userService.getId());
        obj.setDeletedAt(new Date());
        save(obj);
    }
}
