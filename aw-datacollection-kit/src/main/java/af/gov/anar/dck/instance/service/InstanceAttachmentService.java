package af.gov.anar.dck.instance.service;

import java.util.List;

import af.gov.anar.dck.instance.model.Instance;
import af.gov.anar.dck.instance.model.InstanceAttachment;

public interface InstanceAttachmentService {
	public InstanceAttachment create(Instance instance, String attachment);
	public InstanceAttachment create(InstanceAttachment instanceAttachment);
    public boolean delete(Long id);
    public InstanceAttachment findById(Long id);
    public List findAllByForm(Long instanceId);
}