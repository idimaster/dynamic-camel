package init;

import data.EvaluationRequest;
import data.EvaluationResponse;
import org.apache.camel.ExchangePattern;
import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class EvaluationController {
    @Autowired
    private ProducerTemplate producerTemplate;

    @PostMapping(value = "/v1/evaluate")
    @ResponseBody
    public EvaluationResponse evaluate(@RequestBody EvaluationRequest request) {
        EvaluationResponse response = (EvaluationResponse) producerTemplate.sendBody(EvaluationCamelRouter.ROUTE_NAME, ExchangePattern.InOut, request);
        return response;
    }
}
