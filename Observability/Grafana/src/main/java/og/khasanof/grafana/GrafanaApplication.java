package og.khasanof.grafana;

import io.micrometer.common.KeyValue;
import io.micrometer.common.KeyValues;
import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationHandler;
import io.micrometer.observation.ObservationRegistry;
import io.micrometer.observation.ObservationTextPublisher;
import io.micrometer.observation.annotation.Observed;
import io.micrometer.observation.aop.ObservedAspect;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.observation.DefaultServerRequestObservationConvention;
import org.springframework.http.server.observation.ServerRequestObservationContext;
import org.springframework.http.server.observation.ServerRequestObservationConvention;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.filter.ServerHttpObservationFilter;

import java.util.stream.StreamSupport;

@SpringBootApplication
public class GrafanaApplication {

    public static void main(String[] args) {
        SpringApplication.run(GrafanaApplication.class, args);
    }

}

@Slf4j
@Configuration(proxyBeanMethods = false)
class GrafanaConfiguration {

    @Bean
    public ObservationHandler<Observation.Context> observationTextPublisher() {
        return new ObservationTextPublisher(log::info);
    }

    // To have the @Observed support we need to register this aspect
    @Bean
    ObservedAspect observedAspect(ObservationRegistry observationRegistry) {
        return new ObservedAspect(observationRegistry);
    }

    // if an ObservationRegistry is configured
    @ConditionalOnBean(ObservationRegistry.class)
    // if we do not use Actuator
    @ConditionalOnMissingBean(ServerHttpObservationFilter.class)
    @Bean
    public ServerHttpObservationFilter observationFilter(ObservationRegistry registry) {
        return new ServerHttpObservationFilter(registry);
    }

}

@Slf4j
@Component
class MyObservationHandler implements ObservationHandler<Observation.Context> {

    @Override
    public void onStart(Observation.Context context) {
        context.put("my.key", "jeck!!!");
        log.info("Before running the observation for context [{}], userType [{}]", context.getName(), getUserTypeFromContext(context));
    }

    @Override
    public void onStop(Observation.Context context) {
        log.info("After running the observation for context [{}], userType [{}]", context.getName(), getUserTypeFromContext(context));
    }

    @Override
    public boolean supportsContext(Observation.Context context) {
        return true;
    }

    private String getUserTypeFromContext(Observation.Context context) {
        return StreamSupport.stream(context.getLowCardinalityKeyValues().spliterator(), false)
                .filter(keyValue -> "userType".equals(keyValue.getKey()))
                .map(KeyValue::getValue)
                .findFirst()
                .orElse("UNKNOWN");
    }

}

class ExtendedServerRequestObservationConvention extends DefaultServerRequestObservationConvention {

    @Override
    public KeyValues getLowCardinalityKeyValues(ServerRequestObservationContext context) {
        // here, we just want to have an additional KeyValue to the observation, keeping the default values
        return super.getLowCardinalityKeyValues(context).and(custom(context));
    }

    private KeyValue custom(ServerRequestObservationContext context) {
        return KeyValue.of("custom.method", context.getCarrier().getMethod());
    }

}

class CustomServerRequestObservationConvention implements ServerRequestObservationConvention {

    @Override
    public String getName() {
        // will be used as the metric name
        return "http.server.requests";
    }

    @Override
    public String getContextualName(ServerRequestObservationContext context) {
        // will be used for the trace name
        return "http " + context.getCarrier().getMethod().toLowerCase();
    }

}

@Slf4j
@RestController
class LoggerController {

    @RequestMapping(value = "/hello/people")
    public ResponseEntity<String> logger() {
        return new ResponseEntity<>("Hello People!", HttpStatus.OK);
    }

    @Observed(name = "user.name",
            contextualName = "getting-user-name",
            lowCardinalityKeyValues = {"userType", "userType2"}
    )
    @GetMapping(value = "/user/{userId}")
    public ResponseEntity<String> userInfo(@PathVariable String userId) {
        log.info("Got a request");
        return new ResponseEntity<>("Hello ".concat(userId), HttpStatus.OK);
    }

}
