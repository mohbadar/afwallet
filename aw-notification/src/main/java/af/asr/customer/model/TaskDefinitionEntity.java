package af.asr.customer.model;

import af.asr.infrastructure.revision.AuditEnabledEntity;
import lombok.*;
import org.hibernate.envers.Audited;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "task_definition")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
@Audited
public class TaskDefinitionEntity extends AuditEnabledEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "identifier")
    private String identifier;
    @Column(name = "a_type")
    private String type;
    @Column(name = "a_name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "assigned_commands")
    private String assignedCommands;
    @Column(name = "mandatory")
    private Boolean mandatory;
    @Column(name = "predefined")
    private Boolean predefined;


}