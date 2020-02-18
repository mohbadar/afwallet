package af.gov.anar.ebreshna.common.office.model;

import af.gov.anar.ebreshna.common.base.BaseEntity;
import af.gov.anar.ebreshna.infrastructure.util.Schema;
import af.gov.anar.ebreshna.common.office.enumeration.GovOrgType;
import lombok.*;
import org.hibernate.envers.Audited;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "crm_gov_code_master", schema = Schema.CORE_SCHEMA)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
@Audited
public class GovCodeMaster extends BaseEntity {

    @Column(nullable = false)
    private GovOrgType govOrgType;
    @Column(unique = true, nullable = false)
    private String govCode;
    @Column(nullable = false)
    private String organizationName;
}
