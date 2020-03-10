package af.gov.anar.dck.form.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import af.gov.anar.dck.form.model.Form;

import java.util.List;

public interface FormRepository extends JpaRepository<Form, Long> {
	public List<Form> findByShowOnMap(Boolean showOnMap);
	public List<Form> findByHasGeometry(Boolean hasGeometry);

	public List<Form> findByShowOnMapAndHasGeometry(Boolean showOnMap, Boolean hasGeometry);

	public List<Form> findByFormCategory(String formCategory);
	
	@Query("SELECT new af.gov.anar.dck.form.model.Form(f.id, f.name, f.description, f.xmlFormId, f.formType, f.formCategory, f.defaultWorkflowStep, f.active, f.showOnMap, f.envSlug, f.schemaETag, f.createdAt, f.updatedAt) from Form f where f.formCategory=?1 and f.envSlug=?2 order by f.id")
	public List<Form> findByFormCategoryAndEnvSlug(int formCategory, String envSlug);

	public Form findByXmlFormId(String xmlFormId);

	@Query("SELECT new af.gov.anar.dck.form.model.Form(f.id, f.name, f.description, f.xmlFormId, f.formType, f.formCategory, f.defaultWorkflowStep, f.active, f.showOnMap, f.envSlug, f.schemaETag, f.createdAt, f.updatedAt) from Form f order by f.id")
	public List<Form> findAllWithoutXMLContent();

	@Query("SELECT new af.gov.anar.dck.form.model.Form(f.id, f.name, f.description, f.xmlFormId, f.formType, f.formCategory, f.defaultWorkflowStep, f.active, f.showOnMap, f.envSlug, f.schemaETag, f.createdAt, f.updatedAt) from Form f where f.envSlug=?1 order by f.id")
	public List<Form> findAllWithoutXMLContentByEnv(String envSlug);

	@Query("SELECT new af.gov.anar.dck.form.model.Form(f.id, f.name, f.description, f.xmlFormId, f.formType, f.formCategory, f.active, f.createdAt, COUNT(i.id)) from Form f LEFT JOIN f.instances i LEFT JOIN f.groups g where g.id IN (?1) group by f.id")
	public List<Form> findByGroups(List<Long> groupIds);

	@Query("SELECT new af.gov.anar.dck.form.model.Form(f.id, f.name , f.formType, f.active, f.formCategory, f.envSlug, f.showOnMap, f.hasGeometry, f.properties) from Form f LEFT JOIN f.groups g where g.id  IN (?1) and f.showOnMap=?1 and f.hasGeometry=?2 group by f.id")
	public List<Form> findByGroupsShowOnMapHasGeometry(List<Long> groupIds, boolean showOnMap, boolean hasGeometry);

	@Query("SELECT new af.gov.anar.dck.form.model.Form(f.id, f.name, f.description, f.xmlFormId, f.formType, f.formCategory, f.active, f.createdAt, COUNT(i.id)) from Form f LEFT JOIN f.instances i LEFT JOIN f.groups g where g.id IN (?1) and f.envSlug=?2 group by f.id")
	public List<Form> findByGroupsAndEnv(List<Long> groupIds, String envSlug);

	@Query("SELECT new af.gov.anar.dck.form.model.Form(f.id, f.name, f.description, f.xmlFormId, f.formType, f.formCategory, f.active, f.createdAt, COUNT(i.id)) from Form f LEFT JOIN f.instances i group by f.id")
	public List<Form> findAllWithInstanceCount();

	@Query("SELECT new af.gov.anar.dck.form.model.Form(f.id, f.name, f.description, f.xmlFormId, f.formType, f.formCategory, f.active, f.createdAt, COUNT(i.id)) from Form f LEFT JOIN f.instances i where f.envSlug=?1 group by f.id")
	public List<Form> findAllWithInstanceCountByEnv(String envSlug);

	@Query("SELECT new af.gov.anar.dck.form.model.Form(f.id, f.name , f.formType, f.active, f.formCategory, f.envSlug, f.showOnMap, f.hasGeometry, f.properties) from Form f where f.showOnMap=?1 and f.hasGeometry=?2 order by f.id")
	public List<Form> findAllWithoutXMLContentByShowOnMapHasGeometry(boolean showOnMap, boolean hasGeometry);			

	/**
	 * 
	 * @param id
	 * @return List of child forms for a specific id
	 */
	@Query("SELECT new af.gov.anar.dck.form.model.Form(f.id, f.name, f.description, f.createdAt) from Form f INNER JOIN f.childFormOf cfOf where cfOf.id = ?1 GROUP BY f.id")
	public List<Form> findByParentFormIdWithoutXMLContent(Long id);

	long countByEnvSlug(String envSlug);
	long countByEnvSlugAndActive(String envSlug, boolean active);

}
