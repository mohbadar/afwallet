package af.gov.anar.dck.instance.service;

import af.gov.anar.dck.instance.model.Instance;
import af.gov.anar.dck.instance.model.InstanceAttachment;
import af.gov.anar.dck.instance.model.InstanceComment;
import af.gov.anar.dck.instance.model.InstanceHistory;
import af.gov.anar.dck.instance.repository.InstanceCommentRepository;
import af.gov.anar.dck.useradministration.model.User;
import af.gov.anar.dck.useradministration.service.UserService;

import net.sf.jasperreports.engine.JRException;
import org.springframework.core.io.Resource;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface InstanceService {
    public Long getCountByFormId(Long formId);

    public List<Instance> create_batch(List<Instance> instances);

    public Instance create(Instance instance);

    public Instance create(Long formId);

    public Instance create(Long formId, Long parentInstanceId, String formName);

    public Instance create(Long formId, User user);

    public Instance create(Long formId, User user, Long parentInstanceId, String formName);

    public List<Instance> getChildInstances(Long instanceId);

    public List<Instance> getChildInstancesWithoutXMLContent(Long instanceId);

    public Object[] getChildInstancesWithoutXMLContent(DataTablesInput input, Long instanceId);

    public Instance addImage(Instance instance, String fieldName, File savedImageFile, boolean isRepeatField);

    public Resource loadFileAsResource(String fileName) throws IOException;

    // public Instance delete(Long id);
    public boolean delete(Long id);

    public boolean deleteInstanceHistory(Long instanceId);
    public boolean deleteInstanceComments(Long instanceId);
    public boolean deleteInstanceTransitions(Long instanceId);

    public List findAllByForm(Long formId);

    public List findAllByFormWithoutXMLContent(Long formId);

    public Object[] findAllByFormWithoutXMLContent(DataTablesInput input, Long formid);

    public Instance findById(Long id);

    public boolean update(Long id, String payload, boolean notifyWatchers);

    public boolean update(Long id, Instance instance, String envSlug);

    public boolean detach(Long id);

    public String getInstanceFolderPath(Long id) throws IOException;

    public String getInstanceFolderPath(Instance instance) throws IOException;

    public String getBlankInstance(Long formId);

    public List populateInstanceProperties(List<Instance> instances);

    public Instance populateInstanceProperties(Instance instance);

    public File generatePdfJasperReport(Long instanceId, Long reportId) throws IOException, JRException;

    public long countByEnvSlug(String envSlug);

    public long countByEnvSlugAndActive(String envSlug, Boolean active);

    public Instance createInstanceDirectory(Instance instance);

    public Instance deleteImage(Instance instance, String imageName, String fieldName, boolean isRepeatField);

    // ODKX relevent functions
    public List<Instance> getInstancesExistFromList(Long formId, List<String> rowIds);

    public List<Instance> findByFormIdAndRowId(Long formId, String rowId);

    public Instance cloneInstance(Long instanceId, User loggedInUser) ;
    
    public List<Instance> hasChildren();
}
