package af.gov.anar.dck.common.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity(name = "KeyValue")
@Table(name = "key_value")
public class KeyValue {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "key_value_generator")
    @SequenceGenerator(name = "key_value_generator", sequenceName = "key_value_seq", allocationSize = 1)
    @Column(unique = true, updatable = false, nullable = false)
    private Long id;

    @Column(unique = true, name = "key")
    private String key;

    @Column(name = "value")
    private String value;
    
    public KeyValue(String key, String value) {
        this.key = key;
        this.value = value;
    }

    @Override
	public String toString() {
        return "KeyValue [id=" + id + ", key=" + key + ", value=" + value + "]";
	}
}
