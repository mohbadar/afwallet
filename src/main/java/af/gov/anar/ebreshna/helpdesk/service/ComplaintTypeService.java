package af.gov.anar.ebreshna.helpdesk.service;

import af.gov.anar.ebreshna.helpdesk.model.Comment;
import af.gov.anar.ebreshna.helpdesk.model.Complaint;
import af.gov.anar.ebreshna.helpdesk.model.ComplaintType;
import af.gov.anar.ebreshna.helpdesk.repository.CommentRepository;
import af.gov.anar.ebreshna.helpdesk.repository.ComplaintTypeRepository;
import af.gov.anar.ebreshna.infrastructure.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

public class ComplaintTypeService {

    @Autowired
    private ComplaintTypeRepository repository;

    @Autowired
    private UserService userService;

    public ComplaintType save(ComplaintType obj)
    {
        return repository.save(obj);
    }

    public List<ComplaintType> findall()
    {
        return repository.findAll();
    }

    public ComplaintType findOne(long id)
    {
        return repository.getOne(id);
    }

    public void delete(long id)
    {
        ComplaintType obj = repository.getOne(id);
        obj.setDeleted(true);
        obj.setUserId(userService.getId());
        obj.setDeletedAt(new Date());
        save(obj);
    }
}
