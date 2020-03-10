
package af.gov.anar.dck.workflow.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import af.gov.anar.dck.infrastructure.logger.Loggable;
import af.gov.anar.dck.useradministration.service.UserService;
import af.gov.anar.dck.workflow.model.Workflow;
import af.gov.anar.dck.workflow.repository.WorkflowRepository;

import java.util.List;


@Service
@Slf4j
public class WorkflowServiceImpl implements WorkflowService {
    
    @Autowired
    private WorkflowRepository workflowRepository;
    @Autowired
    private UserService userService;

    @Loggable
    @Override
    @Retryable(
            value = {Exception.class},
            maxAttempts = 3,
            backoff = @Backoff(delay = 1000))
    public Workflow createOrUpdate(Workflow workflow) {
        workflow.setEnvSlug(userService.getCurrentEnv());
        return workflowRepository.save(workflow);
    }

    @Loggable
    @Override
    @Retryable(
            value = {Exception.class},
            maxAttempts = 3,
            backoff = @Backoff(delay = 1000))
    public Workflow update(Workflow workflow, Long id) {
        workflow.setId(id);
        workflow.setEnvSlug(userService.getCurrentEnv());
        return workflowRepository.save(workflow);
    }

    @Loggable
    @Override
    @Retryable(value = {Exception.class}, maxAttempts = 3, backoff = @Backoff(delay = 1000))
    public List<Workflow> findAll() {
        return workflowRepository.findAll();
    }

    @Loggable
    @Override
    @Retryable(value = {Exception.class}, maxAttempts = 3, backoff = @Backoff(delay = 1000))
    public List<Workflow> findAllByEnv(String envSlug) {
        return workflowRepository.findByEnvSlug(envSlug);
    }

    @Loggable
    @Override
    @Retryable(value = {Exception.class}, maxAttempts = 3, backoff = @Backoff(delay = 1000))
    public List<Workflow> findAllWithoutJSONContent() {
        String envSlug = userService.getCurrentEnv();
        return workflowRepository.findAllWithoutJSONContent(envSlug);
    }

    @Loggable
    @Override
    @Retryable(
            value = {Exception.class},
            maxAttempts = 3,
            backoff = @Backoff(delay = 1000))
    public Workflow findById(Long id) {
        return workflowRepository.getOne(id);
    }

    @Loggable
    @Override
    @Retryable(
            value = {Exception.class},
            maxAttempts = 3,
            backoff = @Backoff(delay = 1000))
    public Workflow delete(Long id) {
        Workflow workflow = workflowRepository.getOne(id);
        if (workflow != null) {
            workflowRepository.delete(workflow);
        }
        return workflow;
    }

    @Loggable
    @Override
    @Retryable(
            value = {Exception.class},
            maxAttempts = 3,
            backoff = @Backoff(delay = 1000))
    public void delete(Workflow workflow) {
        if (workflow != null) {
            workflowRepository.delete(workflow);
        }
    }

}
