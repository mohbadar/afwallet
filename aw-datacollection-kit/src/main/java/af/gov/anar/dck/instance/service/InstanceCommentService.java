package af.gov.anar.dck.instance.service;

import java.util.List;

import af.gov.anar.dck.instance.model.Instance;
import af.gov.anar.dck.instance.model.InstanceAttachment;
import af.gov.anar.dck.instance.model.InstanceComment;

import java.util.List;

public interface InstanceCommentService {
	public InstanceComment create(Instance instance, String comment);
	public InstanceComment create(InstanceComment InstanceComment);
    public InstanceComment delete(Long id);
    public boolean deleteByInstance(Long instanceId);
    public InstanceComment findById(Long id);
    public List findAllByForm(Long instanceId);
}