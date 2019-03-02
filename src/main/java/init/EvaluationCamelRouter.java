package init;

import data.EvaluationResponse;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class EvaluationCamelRouter extends RouteBuilder {
    public static final String ROUTE_NAME = "direct:evaluateMain";

    @Override
    public void configure() throws Exception {
        from(ROUTE_NAME)
                .routeId("MainEvaluationFlow")
                .setBody().constant(new EvaluationResponse(UUID.randomUUID()))
                .end();
    }
}
