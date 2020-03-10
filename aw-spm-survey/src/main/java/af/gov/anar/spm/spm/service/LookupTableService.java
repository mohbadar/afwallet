
package af.gov.anar.spm.spm.service;

import af.gov.anar.spm.infrastructure.service.UserService;
import af.gov.anar.spm.spm.domain.LookupTable;
import af.gov.anar.spm.spm.domain.Survey;
import af.gov.anar.spm.spm.repository.LookupTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LookupTableService {

    private final LookupTableRepository lookupTableRepository;
    private final UserService userService;

    @Autowired
    public LookupTableService(final LookupTableRepository lookupTableRepository, final UserService userService) {
        super();

        this.lookupTableRepository = lookupTableRepository;
        this.userService = userService;
    }

    public List<LookupTable> findByKey(final String key) {

        return this.lookupTableRepository.findByKey(key);
    }

    public List<LookupTable> findBySurvey(final Survey survey) {
        return this.lookupTableRepository.findBySurvey(survey);
    }

    public List<LookupTable> createLookupTable(final List<LookupTable> lookupTable) {
        return this.lookupTableRepository.saveAll(lookupTable);
    }
}
