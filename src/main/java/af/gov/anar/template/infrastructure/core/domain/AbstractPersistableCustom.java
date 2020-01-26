
package af.gov.anar.template.infrastructure.core.domain;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.*;
import org.springframework.data.domain.Persistable;


@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public abstract class AbstractPersistableCustom<PK extends Serializable> implements Persistable<Long> {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Override
        public boolean isNew() {

                return null == this.id;
        }

}
