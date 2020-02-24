package af.gov.anar.ebreshna.configuration.payment.model;

import af.gov.anar.ebreshna.configuration.common.BaseEntity;
import af.gov.anar.ebreshna.infrastructure.util.Schema;
import lombok.*;
import org.hibernate.envers.Audited;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "payment_bank_master", schema = Schema.CORE_SCHEMA)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
@Audited
public class BankMaster extends BaseEntity {


    @Column
    private String shortName;

    @Column
    private String bankName;

    @Column
    private String bankCode;

    @Column
    private String logo;

    @Column
    private boolean makerAndCheckerRequired;

    @Column
    private String printType;
}
