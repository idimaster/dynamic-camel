package init;

import data.EvaluationRequest;
import data.EvaluationResponse;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static org.apache.camel.LoggingLevel.ERROR;

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
                .log(ERROR, "Exception: ${exception.message}")
                .log(ERROR, "${exception.stacktrace}")
                .setBody().constant(new EvaluationResponse(UUID.randomUUID(), "42, almost error"));

        from(ROUTE_NAME)
                .routeId("MainEvaluationFlow")
                .recipientList(simple("direct:${body.domain}")).ignoreInvalidEndpoints();

        from("direct:test1")
                .routeId("Domain1")
                .log("Msg: ${body}")
                .enrichWith("direct:test1enrich1").message((oldOne, newOne) -> {
                    if (oldOne == null) {
                        return newOne;
                    }

                    oldOne.setHeader("enrich1", newOne.getBody());
                    return oldOne;
                })
                .log("Msg: ${body}")
                .log("From header: ${header.enrich1}")
                .transform().mvel("[" +
                "new data.CheckRequest('authId', request.body.authId)," +
                "new data.CheckRequest('email', request.body.email)," +
                "new data.CheckRequest('trueIP', request.body.trueIP)," +
                "new data.CheckRequest('proxyIP', request.headers.enrich1.proxyIP)," +
                "new data.CheckRequest('deviceId', request.headers.enrich1.deviceId)" +
                "]")
                .split().body().parallelProcessing().aggregationStrategy((oldExchange, newExchange) -> {
                    if (oldExchange == null) {
                        newExchange.getIn().setBody(new EvaluationResponse(UUID.randomUUID(), "PASS"));
                        return newExchange;
                    }
                    return oldExchange;
                })
                .to("direct:test1process1")
                //.setBody().constant(new EvaluationResponse(UUID.randomUUID(), "PASS 1"))
                .end();

        from("direct:test1enrich1")
                .routeId("Domain1_Enrich")
                .log("Msg: ${body}")
                .transform().mvel("new data.EnrichRequest(request.body.sessionId)")
                .log("Msg: ${body}")
                .marshal().json(JsonLibrary.Jackson)
                .setHeader(Exchange.HTTP_METHOD, simple("POST"))
                .enrich("http4:localhost:8080/s2/enrich?bridgeEndpoint=true")
                .unmarshal().json(JsonLibrary.Jackson)
                .end();

        from("direct:test1process1")
                .routeId("Domain1_Process")
                .log("Msg: ${body}")
                .end();

        from("direct:test2")
                .routeId("Domain2")
                .log("Msg: ${body}")
                .setBody().constant(new EvaluationResponse(UUID.randomUUID(), "PASS 2"))
                .end();

        rest("v2")
                .get("/{id}").description("Find by id").outType(EvaluationResponse.class)
                .to("direct:test1")

                .post("/evaluate").description("Evaluate").type(EvaluationRequest.class).outType(EvaluationResponse.class)
                .to(ROUTE_NAME);
    }
}
