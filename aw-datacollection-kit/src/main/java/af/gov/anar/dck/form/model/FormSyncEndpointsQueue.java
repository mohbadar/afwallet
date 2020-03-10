package af.gov.anar.dck.form.model;

import af.gov.anar.dck.infrastructure.util.enumeration.FormSyncStatus;
import af.gov.anar.dck.instance.model.Instance;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity(name = "FormSyncEndpointsQueue")
@Table(name = "form_sync_endpoints_queue")
public class FormSyncEndpointsQueue {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "form_sync_endpoints_generator")
    @SequenceGenerator(name = "form_sync_endpoints_generator", sequenceName = "form_sync_endpoints_seq", allocationSize = 1)
    @Column(unique = true, updatable = false, nullable = false)
    private Long id;

    @Column(nullable = false, name = "name", unique = true)
    private String name;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "form_id")
    private Form form;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "instance_id")
    private Instance instance;


    @Column(nullable = false, name = "endpoint_url")
    private String endpointUrl;

    @Column(name = "sync_status")
    private FormSyncStatus formSyncStatus;


    @Column(name = "endpoint_response")
    private String endpointResponse;


    @Column(name = "env_slug")
    private String envSlug;

    //sync Type  -- only data or data + attachement + attachement

    //which fields to send 


	@Column(name = "created_at")
	@CreationTimestamp
	private LocalDateTime createdAt;

	@Column(name = "updated_at")
	@UpdateTimestamp
	private LocalDateTime updatedAt;
    
}