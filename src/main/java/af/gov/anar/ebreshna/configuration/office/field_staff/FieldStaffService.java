package af.gov.anar.ebreshna.configuration.office.field_staff;

import af.gov.anar.ebreshna.configuration.office.field_staff.FieldStaff;
import af.gov.anar.ebreshna.configuration.office.field_staff.FieldStaffRepository;
import af.gov.anar.ebreshna.infrastructure.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class FieldStaffService {

    @Autowired
    private FieldStaffRepository repository;

    @Autowired
    private UserService userService;

    public FieldStaff save(FieldStaff obj)
    {
        return repository.save(obj);
    }

    public List<FieldStaff> findAll()
    {
        return repository.findAll();
    }

    public FieldStaff findOne(long id)
    {
        return repository.getOne(id);
    }

    public void delete(long id)
    {
        FieldStaff obj = repository.getOne(id);
        obj.setDeleted(true);
        obj.setUserId(userService.getId());
        obj.setDeletedAt(new Date());
        save(obj);
    }
}
