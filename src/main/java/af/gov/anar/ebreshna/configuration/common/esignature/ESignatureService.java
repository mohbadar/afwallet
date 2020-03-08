package af.gov.anar.ebreshna.configuration.common.esignature;

import af.gov.anar.ebreshna.configuration.common.fee.model.FeeType;
import af.gov.anar.ebreshna.configuration.common.fee.repository.FeeTypeRepository;
import af.gov.anar.ebreshna.infrastructure.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ESignatureService {

    @Autowired
    private ESignatureRepository repository;

    @Autowired
    private UserService userService;


    public ESignature save(ESignature obj)
    {
        System.out.println("ESING : "+ obj.toString());
        ESignature previousData = repository.findByOwner(userService.getPreferredUsername());
        if (previousData != null)
        {
            obj.setId(previousData.getId());
        }

        obj.setOwner(userService.getPreferredUsername());
        return repository.save(obj);
    }

    public List<ESignature> findall()
    {
        return repository.findAll();
    }

    public ESignature findOne(long id)
    {
        return repository.getOne(id);
    }

    public ESignature findByLoggedInUser()
    {
        return repository.findByUserId(userService.getId());
    }

    public void delete(long id)
    {
        ESignature obj = repository.getOne(id);
        obj.setDeleted(true);
        obj.setUserId(userService.getId());
        obj.setDeletedAt(new Date());
        save(obj);
    }

}
