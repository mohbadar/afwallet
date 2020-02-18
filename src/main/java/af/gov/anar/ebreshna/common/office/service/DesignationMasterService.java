package af.gov.anar.ebreshna.common.office.service;

import af.gov.anar.ebreshna.common.office.model.DesignationMaster;
import af.gov.anar.ebreshna.common.office.repository.DesignationMasterRepository;
import af.gov.anar.ebreshna.helpdesk.model.Comment;
import af.gov.anar.ebreshna.helpdesk.repository.CommentRepository;
import af.gov.anar.ebreshna.infrastructure.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.security.krb5.internal.crypto.Des;

import java.util.Date;
import java.util.List;

@Service
public class DesignationMasterService {

    @Autowired
    private DesignationMasterRepository repository;

    @Autowired
    private UserService userService;

    public DesignationMaster save(DesignationMaster obj)
    {
        return repository.save(obj);
    }

    public List<DesignationMaster> findall()
    {
        return repository.findAll();
    }

    public DesignationMaster findOne(long id)
    {
        return repository.getOne(id);
    }

    public void delete(long id)
    {
        DesignationMaster obj = repository.getOne(id);
        obj.setDeleted(true);
        obj.setUserId(userService.getId());
        obj.setDeletedAt(new Date());
        save(obj);
    }
}
