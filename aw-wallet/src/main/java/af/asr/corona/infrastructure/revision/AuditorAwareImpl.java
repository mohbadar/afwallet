package af.asr.corona.infrastructure.revision;

import af.asr.corona.infrastructure.base.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuditorAwareImpl implements AuditorAware<String> {

    @Autowired
    private UserService userService;

    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of(userService.getPreferredUsername());
    }

}