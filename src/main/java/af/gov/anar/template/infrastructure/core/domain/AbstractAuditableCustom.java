package af.gov.anar.template.infrastructure.core.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.joda.time.DateTime;
import org.springframework.data.domain.Auditable;
import org.springframework.data.jpa.domain.AbstractAuditable;

/**
 * A custom copy of {@link AbstractAuditable} to override the column names used
 * on database.
 *
 * Abstract base class for auditable entities. Stores the audition values in
 * persistent fields.
 *
 * @param <U>
 *            the auditing type. Typically some kind of user.
 * @param <PK>
 *            the type of the auditing type's identifier
 */
@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class AbstractAuditableCustom<U, PK extends Serializable> extends AbstractPersistableCustom<PK>{

    private static final long serialVersionUID = 141481953116476081L;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "createdby_id")
    private String createdBy;

    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "lastmodifiedby_id")
    private String lastModifiedBy;

    @Column(name = "lastmodified_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

}
