package af.gov.anar.template.organization.api;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
//@RequestMapping(value = "")
public class TestApiResouce {

    @GetMapping(value = "/api/works", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String,String> test()
    {
        Map<String, String> data = new HashMap<>();
        data.put("data", "test");

        System.out.println("test()");
        return data;
    }
}
