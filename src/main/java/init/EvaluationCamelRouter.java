package init;

import data.EvaluationRequest;
import data.EvaluationResponse;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class EvaluationCamelRouter extends RouteBuilder {
    public static final String ROUTE_NAME = "direct:evaluateMain";

    @Override
    public void configure() {
        restConfiguration()
                .bindingMode(RestBindingMode.json)
                .dataFormatProperty("prettyPrint", "true")
                .apiContextPath("/api-doc")
                .apiProperty("api.title", "Evaluation API").apiProperty("api.version", "1.0")
                .apiProperty("cors", "true");

        onException(Exception.class)
                .handled(true)
                .setBody().constant(new EvaluationResponse(UUID.randomUUID(), "42"));

        from(ROUTE_NAME)
                .routeId("MainEvaluationFlow")
                .recipientList(simple("direct:${body.domain}"));

        from("direct:test1")
                .setBody().constant(new EvaluationResponse(UUID.randomUUID(), "44"))
                .end();

        from("direct:test2")
                .setBody().constant(new EvaluationResponse(UUID.randomUUID(), "45"))
                .end();

        rest("v2")
                .get("/{id}").description("Find by id").outType(EvaluationResponse.class)
                .to("direct:test1")

                .post("/evaluate").description("Evaluate").type(EvaluationRequest.class).outType(EvaluationResponse.class)
                .to(ROUTE_NAME);
    }
}
