package init;

import data.CheckRequest;
import data.CheckResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class S1Controller {

    @PostMapping(value = "/s1/check")
    @ResponseBody
    public CheckResponse check(@RequestBody CheckRequest request) {
        CheckResponse response = new CheckResponse();
        response.setName(request.getName());
        if ("email".equals(request.getName())) {
            response.setResult("INVALID");
        } else {
            response.setResult("VALID");
        }
        return response;
    }
}
