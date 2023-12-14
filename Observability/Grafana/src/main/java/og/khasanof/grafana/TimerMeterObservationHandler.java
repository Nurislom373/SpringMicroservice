package og.khasanof.grafana;

import io.micrometer.core.instrument.Tag;
import io.micrometer.core.instrument.Timer;
import io.micrometer.observation.Observation;
import io.micrometer.prometheus.PrometheusMeterRegistry;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Nurislom
 * @see og.khasanof.grafana
 * @since 12/14/2023 4:10 PM
 */
@Component
public class TimerMeterObservationHandler extends AbstractObservationHandler {

    private final PrometheusMeterRegistry meterRegistry;

    public TimerMeterObservationHandler(PrometheusMeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }

    @Override
    public void onStart(Observation.Context context) {
        Timer.Sample sample = Timer.start(meterRegistry);
        context.put(Timer.Sample.class, sample);
    }

    @Override
    public void onStop(Observation.Context context) {
        List<Tag> tags = createTags(context);
        tags.add(Tag.of("error", getErrorValue(context)));
        Timer.Sample sample = context.getRequired(Timer.Sample.class);
        sample.stop(Timer.builder(context.getName()).tags(tags).register(this.meterRegistry));
    }

    @Override
    public boolean supportsContext(Observation.Context context) {
        return true;
    }
}
