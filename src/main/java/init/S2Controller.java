package init;

import data.EnrichRequest;
import data.EnrichResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.UUID;

@Controller
public class S2Controller {
    @PostMapping(value = "/s2/enrich")
    @ResponseBody
    public EnrichResponse check(@RequestBody EnrichRequest request) {
        EnrichResponse response = new EnrichResponse();
        response.setSessionId(request.getSessionId());
        response.setProxyIP("1.1.1.1");
        response.setDeviceId(UUID.randomUUID().toString());
        response.setRecomendation("PASS");
        return response;
    }
}
