package af.gov.anar.ebreshna.configuration.metering.service;

import af.gov.anar.ebreshna.configuration.metering.model.MeterMakeDetail;
import af.gov.anar.ebreshna.configuration.metering.repository.MeterMakeDetailRepository;
import af.gov.anar.ebreshna.infrastructure.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MeterMakeDetailService {

    @Autowired
    private MeterMakeDetailRepository repository;

    @Autowired
    private UserService userService;

    public MeterMakeDetail save(MeterMakeDetail obj)
    {
        return repository.save(obj);
    }

    public List<MeterMakeDetail> findall()
    {
        return repository.findAll();
    }

    public MeterMakeDetail findOne(long id)
    {
        return repository.getOne(id);
    }

    public void delete(long id)
    {
        MeterMakeDetail obj = repository.getOne(id);
        obj.setDeleted(true);
        obj.setUserId(userService.getId());
        obj.setDeletedAt(new Date());
        save(obj);
    }
}
