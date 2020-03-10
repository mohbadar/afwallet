package af.gov.anar.template.sample.model;


import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

import javax.persistence.*;

@Entity
@Table(name = "sample_entity")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
@Audited
/**
 * You can use the @ApiModelProperty annotation to describe the properties of the Product model. With @ApiModelProperty, you can also document a property as required.
 */
public class SampleEntity {

    @Id
    @ApiModelProperty(notes = "The database generated product ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sampleentity_generator")
    @SequenceGenerator(name = "sampleentity_generator", sequenceName = "sampleentity_seq", allocationSize = 1)
    @Column(unique = true, updatable = false, nullable = false)
    private long id;

    @ApiModelProperty(notes = "The name property of sample entity")
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED) // if you don't want to audit this field
    private String name;
}
