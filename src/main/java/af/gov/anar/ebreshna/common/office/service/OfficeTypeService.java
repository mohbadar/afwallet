package af.gov.anar.ebreshna.common.office.service;

import af.gov.anar.ebreshna.common.office.model.OfficeType;
import af.gov.anar.ebreshna.common.office.repository.OfficeTypeRepository;
import af.gov.anar.ebreshna.helpdesk.model.Comment;
import af.gov.anar.ebreshna.helpdesk.repository.CommentRepository;
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
