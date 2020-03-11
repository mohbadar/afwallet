package af.gov.anar.ebreshna.nsc.communication_address;

import af.gov.anar.ebreshna.infrastructure.service.UserService;
import af.gov.anar.ebreshna.nsc.communication_address.CommunicationAddress;
import af.gov.anar.ebreshna.nsc.communication_address.CommunicationAddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CommunicationAddressService {

    @Autowired
    private CommunicationAddressRepository repository;

    @Autowired
    private UserService userService;

    public CommunicationAddress save(CommunicationAddress obj)
    {
        return repository.save(obj);
    }

    public List<CommunicationAddress> findall()
    {
        return repository.findAll();
    }

    public CommunicationAddress findOne(long id)
    {
        return repository.getOne(id);
    }

    public void delete(long id)
    {
        CommunicationAddress obj = repository.getOne(id);
        obj.setDeleted(true);
        obj.setUserId(userService.getId());
        obj.setDeletedAt(new Date());
        save(obj);
    }

}
