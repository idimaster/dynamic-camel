package init;

import data.EvaluationResponse;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class EvaluationCamelRouter extends RouteBuilder {
    public static final String ROUTE_NAME = "direct:evaluateMain";

    @Override
    public void configure() {
        onException(Exception.class)
                .handled(true)
                .setBody().constant(new EvaluationResponse(UUID.randomUUID(), "42"));

        from(ROUTE_NAME)
                .routeId("MainEvaluationFlow")
                .recipientList(simple("direct:${body.domain}"));

        from("direct:test1")
                .setBody().constant(new EvaluationResponse(UUID.randomUUID(), "44"))
                .end();
    }
}
