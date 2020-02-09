package af.gov.anar.ebreshna.common.service;

import af.gov.anar.ebreshna.common.model.Province;
import af.gov.anar.ebreshna.common.repository.ProvinceRepository;
import af.gov.anar.ebreshna.infrastructure.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ProvinceService {

    @Autowired
    private ProvinceRepository repository;

    @Autowired
    private UserService userService;

    public Province save(Province obj)
    {
        return repository.save(obj);
    }

    public List<Province> findall()
    {
        return repository.findAll();
    }

    public Province findOne(long id)
    {
        return repository.getOne(id);
    }

    public void delete(long id)
    {
        Province obj = repository.getOne(id);
        obj.setDeleted(true);
        obj.setUserId(userService.getId());
        obj.setDeletedAt(new Date());
        save(obj);
    }

}
