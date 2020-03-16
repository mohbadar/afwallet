
package af.asr.accounting.model;


import af.asr.infrastructure.converter.LocalDateTimeConverter;

import javax.persistence.*;
import java.time.LocalDateTime;

@SuppressWarnings("unused")
@Entity
@Table(name = "acc_commands", schema = "accounting")
public class CommandEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;
  @OneToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "account_id")
  private AccountEntity account;
  @Column(name = "a_type")
  private String type;
  @Column(name = "a_comment")
  private String comment;
  @Column(name = "created_by")
  private String createdBy;
  @Column(name = "created_on")
  @Convert(converter = LocalDateTimeConverter.class)
  private LocalDateTime createdOn;

  public CommandEntity() {
    super();
  }

  public Long getId() {
    return this.id;
  }

  public void setId(final Long id) {
    this.id = id;
  }

  public AccountEntity getAccount() {
    return this.account;
  }

  public void setAccount(final AccountEntity account) {
    this.account = account;
  }

  public String getType() {
    return this.type;
  }

  public void setType(final String type) {
    this.type = type;
  }

  public String getComment() {
    return this.comment;
  }

  public void setComment(final String comment) {
    this.comment = comment;
  }

  public String getCreatedBy() {
    return this.createdBy;
  }

  public void setCreatedBy(final String createdBy) {
    this.createdBy = createdBy;
  }

  public LocalDateTime getCreatedOn() {
    return this.createdOn;
  }

  public void setCreatedOn(final LocalDateTime createdOn) {
    this.createdOn = createdOn;
  }
}
