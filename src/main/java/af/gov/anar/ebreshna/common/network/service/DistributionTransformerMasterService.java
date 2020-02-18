package af.gov.anar.ebreshna.common.network.service;

import af.gov.anar.ebreshna.common.network.model.DistributionTransformerMaster;
import af.gov.anar.ebreshna.common.network.repository.DistributionTransformerMasterRepository;
import af.gov.anar.ebreshna.common.office.model.DesignationMaster;
import af.gov.anar.ebreshna.common.office.repository.DesignationMasterRepository;
import af.gov.anar.ebreshna.infrastructure.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DistributionTransformerMasterService {

    @Autowired
    private DistributionTransformerMasterRepository repository;

    @Autowired
    private UserService userService;

    public DistributionTransformerMaster save(DistributionTransformerMaster obj)
    {
        return repository.save(obj);
    }

    public List<DistributionTransformerMaster> findall()
    {
        return repository.findAll();
    }

    public DistributionTransformerMaster findOne(long id)
    {
        return repository.getOne(id);
    }

    public void delete(long id)
    {
        DistributionTransformerMaster obj = repository.getOne(id);
        obj.setDeleted(true);
        obj.setUserId(userService.getId());
        obj.setDeletedAt(new Date());
        save(obj);
    }
}
