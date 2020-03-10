package af.gov.anar.dck.datasource.model;
import lombok.*;

import javax.persistence.*;

// @Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
// @EqualsAndHashCode
@Entity
@Table(name = "datasource")
public class Datasource {
 
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "datasource_generator")
	@SequenceGenerator(name="datasource_generator", sequenceName = "datasource_seq", allocationSize = 1)
	@Column(unique = true, updatable = false, nullable = false)
	private Long id;
 
	@Column(name = "name")
	private String name;
 
	@Column(name = "host")
	private String host;
 
	@Column(name = "port")
	private String port;

	@Column(name = "userName")
	private String userName;

	@Column(name = "db_pswrd")
	private String db_pswrd;

	@Column(name = "SSL")
	private String SSL;

	@Column(name = "DBName")
	private String DBName;
	
	@Column(name = "env_slug")
	private String envSlug;
 
	public Datasource(String name, String host) {
		this.name = name;
		this.host = host;
		this.port = port;
	}
}