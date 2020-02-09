package af.gov.anar.ebreshna.helpdesk.service;

import af.gov.anar.ebreshna.helpdesk.model.Comment;
import af.gov.anar.ebreshna.helpdesk.model.Complaint;
import af.gov.anar.ebreshna.helpdesk.model.ComplaintHistory;
import af.gov.anar.ebreshna.helpdesk.repository.CommentRepository;
import af.gov.anar.ebreshna.helpdesk.repository.ComplaintHistoryRepository;
import af.gov.anar.ebreshna.infrastructure.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class ComplaintHistoryService {

    @Autowired
    private ComplaintHistoryRepository repository;

    @Autowired
    private UserService userService;

    public ComplaintHistory save(ComplaintHistory obj)
    {
        return repository.save(obj);
    }

    public List<ComplaintHistory> findall()
    {
        return repository.findAll();
    }

    public ComplaintHistory findOne(long id)
    {
        return repository.getOne(id);
    }

    public void delete(long id)
    {
        ComplaintHistory obj = repository.getOne(id);
        obj.setDeleted(true);
        obj.setUserId(userService.getId());
        obj.setDeletedAt(new Date());
        save(obj);
    }
}
