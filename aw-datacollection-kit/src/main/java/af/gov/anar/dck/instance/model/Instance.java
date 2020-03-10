package af.gov.anar.dck.instance.model;

import af.gov.anar.dck.form.model.Form;
import af.gov.anar.dck.useradministration.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

// @Data
@AllArgsConstructor
@Setter
@Getter
// @ToString(exclude="properties, owner, form, parentInstance, parentInstances, instanceComments, instanceWatchers, instanceAttachments")
// @EqualsAndHashCode
@Entity(name = "Instance")
@Table(name = "instance")
public class Instance {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "instance_generator")
	@SequenceGenerator(name = "instance_generator", sequenceName = "instance_seq", allocationSize = 1)
	@Column(unique = true, updatable = false, nullable = false)
	private Long id;

	@Column(name = "title")
	private String title;

	@Column
	private boolean active;

	@Column(name = "env_slug")
	private String envSlug;

	@Column
	private Integer version;

	// the primary key for a particular row within a table of odkx sync endpoint
	@Column(name = "_row_id")
	private String rowId;

	// identifies a particular revision of a row within a table of odkx sync endpoint
	@Column(name = "_row_etag")
	private String rowETag;

	@Column(name = "current_step", nullable = true, insertable = true, updatable = true)
	private String currentStep;


	@Column(name = "resolution", nullable = true, insertable = true, updatable = true)
	private String resolution;

	// This field is not mapped to database
	@Transient
	private Map<String, Object> properties = new LinkedHashMap<String, Object>();

	@Column(name = "xml_content")
	@Type(type = "text")
	private String xmlContent;

	@Column(name = "json_content")
	@Type(type = "text")
	private String jsonContent;

	@Column(name = "instance_submission")
	private String instanceSubmission;

	@Column(name = "instance_folder_name")
	private String instanceFolderName;

	@Column(name = "instance_start_time")
	private Timestamp instanceStartTime;

	@Column(name = "instance_end_time")
	private Timestamp instanceEndTime;

	@Column(name = "instance_device_id")
	private String instanceDeviceId;

    @Column(name = "is_first_update")
    private boolean isFirstUpdate;

	@Column(name = "created_at")
	@CreationTimestamp
	private LocalDateTime createdAt;

	@Column(name = "updated_at")
	@UpdateTimestamp
	private LocalDateTime updatedAt;

	// It is the person who submit the form
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "owner_id")
	private User owner;

	@ManyToOne
	@JoinColumn(name = "form_id", nullable = false)
	@JsonIgnore
	private Form form;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_instance_id")
	@JsonIgnore
	private Instance parentInstance;

	@OneToMany(mappedBy = "parentInstance")
	@JsonIgnore
	private Collection<Instance> parentInstances;

	@OneToMany(mappedBy = "instance")
	private Collection<InstanceComment> instanceComments;

	@OneToMany(mappedBy = "instance")
	private Collection<InstanceWatcher> instanceWatchers;

	@OneToMany(mappedBy = "instance")
	private Collection<InstanceAttachment> instanceAttachments;

	public Instance() {

	}
	
	public Instance(Instance instance) {
		this.active = instance.isActive();
		this.version = instance.getVersion();
		this.currentStep = instance.getCurrentStep();
		this.resolution = instance.getResolution();
		this.instanceSubmission = instance.getInstanceSubmission();
		this.instanceFolderName = instance.getInstanceFolderName();
		this.instanceStartTime = instance.getInstanceStartTime();
		this.instanceEndTime = instance.getInstanceEndTime();
        this.instanceDeviceId = instance.getInstanceDeviceId();
        this.isFirstUpdate = instance.isFirstUpdate();
		this.envSlug = instance.getEnvSlug();
		this.owner = instance.getOwner();
		this.createdAt = instance.getCreatedAt();
		this.updatedAt = instance.getUpdatedAt();
		this.title = instance.getTitle();
		this.form = instance.getForm();
		this.instanceDeviceId = instance.getInstanceDeviceId();
		this.jsonContent = instance.getJsonContent();
		this.instanceSubmission = instance.getInstanceSubmission();
		this.parentInstance = instance.getParentInstance();
		this.properties = instance.getProperties();
		this.rowETag = instance.getRowETag();
		this.rowId = instance.getRowId();
		this.xmlContent = instance.getXmlContent();
	}

	public Instance(Long id, boolean active, String title, Integer version, String currentStep, String resolution, String instanceSubmission, String instanceFolderName,
			Date instanceStartTime, Date instanceEndTime, String instanceDeviceId ,boolean isFirstUpdate , String envSlug, User owner,
			LocalDateTime createdAt, LocalDateTime updatedAt) {
		this.id = id;
		this.active = active;
		this.version = version;
		this.currentStep = currentStep;
		this.resolution = resolution;
		this.instanceSubmission = instanceSubmission;
		this.instanceFolderName = instanceFolderName;
		this.instanceStartTime = (Timestamp) instanceStartTime;
		this.instanceEndTime = (Timestamp) instanceEndTime;
        this.instanceDeviceId = instanceDeviceId;
        this.isFirstUpdate = isFirstUpdate;
		this.envSlug = envSlug;
		this.owner = owner;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.title = title;
	}

	public Instance(Long id, boolean active, String title, Integer version, String currentStep, String resolution, String instanceSubmission, String instanceFolderName,
			Date instanceStartTime, Date instanceEndTime, String instanceDeviceId, boolean isFirstUpdate, String envSlug, User owner,
			LocalDateTime createdAt, LocalDateTime updatedAt, String xmlContent, String jsonContent) {
		this.id = id;
		this.active = active;
		this.version = version;
		this.currentStep = currentStep;
		this.resolution = resolution;
		this.instanceSubmission = instanceSubmission;
		this.instanceFolderName = instanceFolderName;
		this.instanceStartTime = (Timestamp) instanceStartTime;
		this.instanceEndTime = (Timestamp) instanceEndTime;
        this.instanceDeviceId = instanceDeviceId;
        this.isFirstUpdate = isFirstUpdate;
		this.envSlug = envSlug;
		this.owner = owner;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.xmlContent = xmlContent;
		this.jsonContent = jsonContent;
		this.title = title;
	}

	public Instance(Long id, boolean active, String title, Integer version, String rowId, String rowETag) {
		this.id = id;
		this.rowId = rowId;
		this.rowETag = rowETag;
	}

	public void setProperty(String key, Object value) {
		this.properties.put(key, value);
	}

	public Object getProperty(String key) {
		return this.properties.get(key);
	}

	@Override
	public String toString() {
        return "Instance [title=" + title + ", active=" + active + ", envSlug=" + envSlug + ", created_at=" + createdAt  + ", updated_at=" + updatedAt + "]";
	}
}

