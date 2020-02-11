package af.gov.anar.ebreshna.customerservice.service;

import af.gov.anar.ebreshna.customerservice.model.Request;
import af.gov.anar.ebreshna.customerservice.repository.RequestRepository;
import af.gov.anar.ebreshna.helpdesk.model.Comment;
import af.gov.anar.ebreshna.helpdesk.repository.CommentRepository;
import af.gov.anar.ebreshna.infrastructure.service.UserService;
import org.bouncycastle.cert.ocsp.Req;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RequestService {

    @Autowired
    private RequestRepository repository;

    @Autowired
    private UserService userService;

    public Request save(Request obj)
    {
        return repository.save(obj);
    }

    public List<Request> findall()
    {
        return repository.findAll();
    }

    public Request findOne(long id)
    {
        return repository.getOne(id);
    }

    public void delete(long id)
    {
        Request obj = repository.getOne(id);
        obj.setDeleted(true);
        obj.setUserId(userService.getId());
        obj.setDeletedAt(new Date());
        save(obj);
    }

}
