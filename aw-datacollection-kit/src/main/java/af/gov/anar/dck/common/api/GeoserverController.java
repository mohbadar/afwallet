package af.gov.anar.dck.common.api;

import af.gov.anar.dck.common.auth.FormAuthService;
import af.gov.anar.dck.common.service.GeoserverService;
import af.gov.anar.dck.form.model.Form;
import af.gov.anar.dck.infrastructure.logger.Loggable;
import af.gov.anar.dck.infrastructure.util.JsonParserUtil;
import af.gov.anar.dck.systemregistry.model.SystemRegistry;
import af.gov.anar.dck.systemregistry.service.SystemRegistryService;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api/geoserver/")
public class GeoserverController {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${spring.mail.to}")
    private String emailTo;

    @Value("${geoserver.curlDefault}")
    private String curlDefault;

    @Autowired
    private FormAuthService formAuthService;

    @Autowired
    private JsonParserUtil jsonParserUtil;

    @Autowired
    private GeoserverService geoserverService;

    @Autowired
    private SystemRegistryService systemRegistryService;

    @Loggable
    @GetMapping(value = { "/map_layers/{formId}" })
    public List getFormMapLayers(HttpServletRequest request, @PathVariable("formId") Long formId) throws IOException {

        Form form = formAuthService.findById(formId);
        Collection<SystemRegistry> mapLayers = form.getMapLayers();
        ArrayList<String> mapLayerList = new ArrayList<String>();
        for (SystemRegistry mapLayer : mapLayers) {
            // The problem was with title, it had some {"title":{"en":"Rual_EAS", "ps":}
            JSONObject jsObj = jsonParserUtil.parse(mapLayer.getContent());
            String URL = jsObj.getString("url");
            String url;
            if (jsObj.has("username") && jsObj.has("password")) {
                String username = jsObj.getString("username");
                String password = jsObj.getString("password");
                url = " GET " + URL + " -u " + username + ":" + password;

            } else {
                url = " GET " + URL;
            }
            String command = curlDefault + " " + url;
            mapLayerList.add(IOUtils.toString(geoserverService.executeCommand(command)));
        }
        return mapLayerList;
    }

    @Loggable
    @GetMapping(value = { "/{mapLayerId}" })
    public String getMapLayerById(HttpServletRequest request, @PathVariable("mapLayerId") Long mapLayerId)
            throws IOException {
        SystemRegistry mapLayer = systemRegistryService.findById(mapLayerId);
        JSONObject jsObj = jsonParserUtil.parse(mapLayer.getContent());
        String URL = jsObj.getString("url");
        String url;
        if (jsObj.has("username") && jsObj.has("password")) {
            String username = jsObj.getString("username");
            String password = jsObj.getString("password");
            url = " GET " + URL + " -u " + username + ":" + password;
        } else {
            url = " GET " + URL;
        }
        String command = curlDefault + " " + url;

        return IOUtils.toString(geoserverService.executeCommand(command));
    }

}
