package af.gov.anar.ebreshna.common.office.service;

import af.gov.anar.ebreshna.common.office.model.FieldStaff;
import af.gov.anar.ebreshna.common.office.repository.FieldStaffRepository;
import af.gov.anar.ebreshna.helpdesk.model.Comment;
import af.gov.anar.ebreshna.helpdesk.repository.CommentRepository;
import af.gov.anar.ebreshna.infrastructure.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
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

    public List<FieldStaff> findall()
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
