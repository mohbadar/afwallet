package af.gov.anar.ebreshna.infrastructure.service;


import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.history.RevisionRepository;

public interface BaseRepository<T> extends CrudRepository<T, Long> , RevisionRepository<T,Long,Integer> {

    Iterable<T>  findByDeleted(boolean deleted);
    Iterable<T>  findByDeletedBy(String deletedBy);
}
