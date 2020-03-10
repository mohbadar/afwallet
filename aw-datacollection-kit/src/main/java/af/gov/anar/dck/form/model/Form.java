package af.gov.anar.dck.form.model;

import af.gov.anar.dck.instance.model.Instance;
import af.gov.anar.dck.report.model.Report;
import af.gov.anar.dck.systemregistry.model.SystemRegistry;
import af.gov.anar.dck.useradministration.model.Group;
import af.gov.anar.dck.workflow.model.Workflow;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;

// @Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
// @ToString(exclude="groups, instances, workflow, reports, childForms,
// childFormOf, createdAt, updatedAt")
// @EqualsAndHashCode
@Entity(name = "Form")
@Table(name = "form")

public class Form {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "form_generator")
    @SequenceGenerator(name = "form_generator", sequenceName = "form_seq", allocationSize = 1)
    @Column(unique = true, updatable = false, nullable = false)
    private Long id;

    // this is form id which will be set in odk collect excel file
    // in ODKX context: this fiel will be used as tableId

    // this field must be unique else odk collect form submissio will not be
    // processed
    @Column(name = "xml_form_id")
    private String xmlFormId;

    @Column(name = "name")
    private String name;

    // schemaETag is required if the form is odkx form
    // The odkx sync endpoint server generates a new, unique, schemaETag every time
    // it creates or modifies the table schema. If you create a table, destroy it,
    // then re-create it, the new table will be given a new schemaETag
    @Column(name = "_schema_etag")
    private String schemaETag;

    // Static/Dynamic
    @Column(name = "form_type")
    private String formType;

    // 0: ODK_Collect/ 1: ODK_X
    @Column(name = "form_category", nullable = false, columnDefinition = "Integer default 0", length = 1)
    private Integer formCategory;

    @Column(name = "description")
    private String description;

    @Column(name = "active")
    private boolean active;

    @Getter
    // if the form has property of type geometry then this will set as true
    @Column(name = "has_geometry", nullable = false, columnDefinition = "boolean default false")
    private boolean hasGeometry = false;

    @Column(name = "show_on_map")
    private boolean showOnMap;

    @Column(name = "xml_content")
    @Type(type = "text")
    private String xmlContent;

    @Column(name = "env_slug")
    private String envSlug;

    @Column(name = "form_folder_name")
    private String formFolderName;

    @Column(name = "grid_json", length = 9000)
    private String gridJson;

    // If this is set, then it is asigned to instance current_step when newly created
    @Column(name = "default_workflow_step")
    private String defaultWorkflowStep;

    @Column(name = "properties")
    @Type(type = "text")
    private String properties;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "form_group", joinColumns = @JoinColumn(name = "form_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "group_id", referencedColumnName = "id"))
    @JsonIgnore
    private Collection<Group> groups;

    // @Column(name = "child_forms")
    // private String childForms;

    // This field is not mapped to database
    @Transient
    private Long instanceCount;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "form", fetch = FetchType.LAZY)
    @JsonIgnore
    private Collection<Instance> instances;

    @OneToOne(targetEntity = Workflow.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = true, name = "workflow_id")
    private Workflow workflow;

    @OneToMany(mappedBy = "form", fetch = FetchType.LAZY)
    @JsonIgnore
    private Collection<Report> reports;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "form_form", joinColumns = { @JoinColumn(name = "form_id") }, inverseJoinColumns = {
            @JoinColumn(name = "child_form_id") })
    private Collection<Form> childForms;

    @ManyToMany
    @JoinTable(name = "form_form", joinColumns = { @JoinColumn(name = "child_form_id") }, inverseJoinColumns = {
            @JoinColumn(name = "form_id") })
    @JsonIgnore
    private Collection<Form> childFormOf;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "system_registry_form", joinColumns = @JoinColumn(name = "form_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "system_registry_id", referencedColumnName = "id"))
    @JsonIgnore
    private Collection<SystemRegistry> mapLayers;

    public Form(String name, String description) {
        this.name = name;
        this.description = description;
        this.active = false;
    }

    public Form(Long id, String name, String description, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.createdAt = createdAt;
    }

    public Form(Long id, String name, String description, String xmlFormId, String formType, int formCategory,
            boolean active, LocalDateTime createdAt, Long instanceCount) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.xmlFormId = xmlFormId;
        this.formType = formType;
        this.formCategory = formCategory;
        this.active = active;
        this.createdAt = createdAt;
        this.instanceCount = instanceCount;
    }

    public Form(Long id, String name, String formType, boolean active, int formCategory, String envSlug,
            boolean showOnMap, boolean hasGeometry, String properties) {
        this.id = id;
        this.name = name;
        this.formType = formType;
        this.active = active;
        this.formCategory = formCategory;
        this.envSlug = envSlug;
        this.showOnMap = showOnMap;
        this.hasGeometry = hasGeometry;
        this.properties = properties;
    }

    public Form(Long id, String name, String description, boolean active, boolean showOnMap, String envSlug,
            LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.active = active;
        this.showOnMap = showOnMap;
        this.envSlug = envSlug;
        this.createdAt = createdAt;
    }

    public Form(Long id, String name, String description, String xmlFormId, String formType, int formCategory,String defaultWorkflowStep,
            boolean active, boolean showOnMap, String envSlug, String schemaETag, LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.xmlFormId = xmlFormId;
        this.formType = formType;
        this.formCategory = formCategory;
        // this.childForms = childForms;
        this.defaultWorkflowStep = defaultWorkflowStep;
        this.active = active;
        this.showOnMap = showOnMap;
        this.envSlug = envSlug;
        this.schemaETag = schemaETag;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "Form [id=" + id + ", name=" + name + ", description=" + description + ", active=" + active
                + ", envSlug=" + envSlug + ", showOnMap=" + showOnMap +  ", defaultWorkFlow =" + defaultWorkflowStep + ", hasGeometry=" + hasGeometry + ", created_at="
                + createdAt + ", updated_at=" + updatedAt + "]";
    }
}
