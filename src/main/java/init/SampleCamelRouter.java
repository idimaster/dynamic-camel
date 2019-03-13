package init;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class SampleCamelRouter extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("timer:hello?period={{timer.period}}")
                .routeId("SampleTimer")
                .setBody().constant("Timer route!")
                .to("stream:out")
                .to("mock:result");
    }
}
