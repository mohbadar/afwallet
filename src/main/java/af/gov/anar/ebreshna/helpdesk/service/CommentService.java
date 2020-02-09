package af.gov.anar.ebreshna.helpdesk.service;

import af.gov.anar.ebreshna.helpdesk.model.Comment;
import af.gov.anar.ebreshna.helpdesk.repository.CommentRepository;
import af.gov.anar.ebreshna.infrastructure.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

public class CommentService {

    @Autowired
    private CommentRepository repository;

    @Autowired
    private UserService userService;

    public Comment save(Comment obj)
    {
        return repository.save(obj);
    }

    public List<Comment> findall()
    {
        return repository.findAll();
    }

    public Comment findOne(long id)
    {
        return repository.getOne(id);
    }

    public void delete(long id)
    {
        Comment obj = repository.getOne(id);
        obj.setDeleted(true);
        obj.setUserId(userService.getId());
        obj.setDeletedAt(new Date());
        save(obj);
    }
}
