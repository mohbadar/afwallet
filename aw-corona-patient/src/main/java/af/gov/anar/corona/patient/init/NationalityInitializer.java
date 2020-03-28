package af.gov.anar.corona.patient.init;

import af.gov.anar.corona.patient.model.Nationality;
import af.gov.anar.corona.patient.repository.NationalityRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Component
public class NationalityInitializer {

    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private NationalityRepository nationalityRepository;

    @PostConstruct
    public void  init() throws IOException {
        if(nationalityRepository.count() < 1)
        {
            addNationalitiesIfDoesnotExist();
        }

    }

    private void addNationalitiesIfDoesnotExist() throws IOException {
        JsonNode root =  mapper.readValue(new ClassPathResource("nationalty.json").getFile(), JsonNode.class);

        int i =0;
        do
        {
            nationalityRepository.save(new Nationality(root.get(i).asText()));
            i++;
        }while (root.elements().hasNext());
    }
}
