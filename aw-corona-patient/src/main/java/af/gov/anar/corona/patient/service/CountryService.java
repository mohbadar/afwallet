package af.gov.anar.corona.patient.service;

import af.gov.anar.corona.infrastructure.base.UserService;
import af.gov.anar.corona.patient.model.Country;
import af.gov.anar.corona.patient.repository.CountryRepository;
import af.gov.anar.lib.logger.annotation.Loggable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Service
public class CountryService  {

    @Autowired
    private CountryRepository repository;

    @Autowired
    private UserService userService;

    @Loggable
    @Retryable(
            value = {Exception.class},
            maxAttempts = 3,
            backoff = @Backoff(delay = 2000))
    @Transactional
    public Country save(Country obj)
    {
        return repository.save(obj);
    }

    @Loggable
    @Retryable(
            value = {Exception.class},
            maxAttempts = 3,
            backoff = @Backoff(delay = 1000))
    public Iterable<Country> findAll(){
        return repository.findAll();
    }

    @Loggable
    @Retryable(
            value = {Exception.class},
            maxAttempts = 3,
            backoff = @Backoff(delay = 1000))
    public Optional<Country> findById(Long id)
    {
        return  repository.findById(id);
    }

    @Loggable
    @Retryable(
            value = {Exception.class},
            maxAttempts = 3,
            backoff = @Backoff(delay = 1000))
    public Iterable<Country> findAllThatIsNotDeleted()
    {
        return  repository.findByDeleted(false);
    }

    @Loggable
    @Retryable(
            value = {Exception.class},
            maxAttempts = 3,
            backoff = @Backoff(delay = 1000))
    public Iterable<Country> findAllThatIsDeleted()
    {
        return  repository.findByDeleted(true);
    }


    @Loggable
    @Retryable(
            value = {Exception.class},
            maxAttempts = 3,
            backoff = @Backoff(delay = 1000))
    @Async
    @Transactional
    public void delete(Long id)
    {
        Country obj = repository.findById(id).get();
        obj.setDeleted(true);
        obj.setDeletedBy(userService.getId());
        obj.setDeletedAt(new Date());
        save(obj);
    }

    @Loggable
    public boolean existsById(long id)
    {
        return repository.existsById(id);
    }

    @Loggable
    public long count()
    {
        return repository.count();
    }
}
