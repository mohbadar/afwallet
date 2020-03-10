package af.gov.anar.dck.instance.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import af.gov.anar.dck.instance.model.Instance;

import java.util.List;

public interface InstanceRepository extends JpaRepository<Instance, Long>  {
	public List<Instance> findByFormId(Long formId);

	// @Query("SELECT * from Instance i where i.form.id=?1 and i.rowId=?2 order by i.id")	
	public List<Instance> findByFormIdAndRowId(Long formId, String rowId);

	@Query("SELECT new af.gov.anar.dck.instance.model.Instance(i.id, i.active, i.title, i.version, i.currentStep, i.resolution, i.instanceSubmission, i.instanceFolderName, i.instanceStartTime, i.instanceEndTime, i.instanceDeviceId, i.isFirstUpdate, i.envSlug, i.owner, i.createdAt, i.updatedAt) from Instance i left join i.owner where i.form.id=?1 order by i.id")
	public List<Instance> findByFormIdWithoutXMLContent(Long formId);

	@Query("SELECT new af.gov.anar.dck.instance.model.Instance(i.id, i.active, i.title, i.version, i.currentStep, i.resolution, i.instanceSubmission, i.instanceFolderName, i.instanceStartTime, i.instanceEndTime, i.instanceDeviceId, i.isFirstUpdate, i.envSlug, i.owner, i.createdAt, i.updatedAt, i.xmlContent, i.jsonContent) from Instance i  left join i.parentInstance where i.parentInstance.id=?1 order by i.id")
	public List<Instance> getChildInstances(Long instanceId);

	@Query("SELECT new af.gov.anar.dck.instance.model.Instance(i.id, i.active, i.title, i.version, i.currentStep, i.resolution, i.instanceSubmission, i.instanceFolderName, i.instanceStartTime, i.instanceEndTime, i.instanceDeviceId, i.isFirstUpdate, i.envSlug, i.owner, i.createdAt, i.updatedAt) from Instance i  left join i.parentInstance where i.parentInstance.id=?1 order by i.id")
	public List<Instance> getChildInstancesWithoutXMLContent(Long parentInstanceId);

	@Query("SELECT new af.gov.anar.dck.instance.model.Instance(i.id, i.active, i.title, i.version, i.currentStep, i.resolution, i.instanceSubmission, i.instanceFolderName, i.instanceStartTime, i.instanceEndTime, i.instanceDeviceId, i.isFirstUpdate,i.envSlug, i.owner, i.createdAt, i.updatedAt) from Instance i order by i.id")
	public List<Instance> getAllInstancesWithoutXMLContent();

	@Query("SELECT count(*) from Instance i  where i.form.id=?1")
	long countByFormId(Long formId);
	long countByEnvSlug(String envSlug);
	long countByEnvSlugAndActive(String envSlug, boolean active);

	@Query("SELECT new af.gov.anar.dck.instance.model.Instance(i.id, i.active, i.title, i.version, i.rowId, i.rowETag) FROM Instance i  where i.form.id=?1 and i.rowId IN (?2) order by i.rowId")
	public List<Instance> getInstancesExistFromList(Long formId, List<String> rowIds);

	@Query("SELECT parentInstance from Instance")
	public List<Instance> hasChildren();

}
