package af.gov.anar.dck.useradministration.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@Setter
@Getter

@Entity
@Table(name = "password_reset_token")
public class PasswordResetToken {
  
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(name = "token")
    private String token;

    @Column(name = "active")
    private Boolean active;
  
    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private User user;
  
    @Basic(optional = false)
    @Column(name= "expiry_date", insertable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date expiryDate;

    public PasswordResetToken(String token, User user, boolean active){
        this.token = token;
        this.user = user;
        this.active = active;
    }

    public PasswordResetToken(){}
}