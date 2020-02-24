package af.gov.anar.ebreshna.configuration.csc.service;

import af.gov.anar.ebreshna.configuration.csc.model.RequestType;
import af.gov.anar.ebreshna.configuration.csc.repository.RequestTypeRepository;
import af.gov.anar.ebreshna.infrastructure.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RequestTypeService {

    @Autowired
    private RequestTypeRepository repository;

    @Autowired
    private UserService userService;

    public RequestType save(RequestType obj)
    {
        return repository.save(obj);
    }

    public List<RequestType> findall()
    {
        return repository.findAll();
    }

    public RequestType findOne(long id)
    {
        return repository.getOne(id);
    }

    public void delete(long id)
    {
        RequestType obj = repository.getOne(id);
        obj.setDeleted(true);
        obj.setUserId(userService.getId());
        obj.setDeletedAt(new Date());
        save(obj);
    }
}
