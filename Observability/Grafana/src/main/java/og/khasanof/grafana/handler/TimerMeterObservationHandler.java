package og.khasanof.grafana.handler;

import io.micrometer.core.instrument.Tag;
import io.micrometer.core.instrument.Timer;
import io.micrometer.observation.Observation;
import io.micrometer.prometheus.PrometheusMeterRegistry;
import og.khasanof.grafana.AbstractObservationHandler;
import og.khasanof.grafana.enumeration.ObserveType;
import og.khasanof.grafana.utils.MeterSuffixes;
import org.springframework.stereotype.Component;

import java.util.List;

import static og.khasanof.grafana.utils.BaseUtils.concat;

/**
 * @author Nurislom
 * @see og.khasanof.grafana
 * @since 12/14/2023 4:10 PM
 */
@Component
public class TimerMeterObservationHandler extends AbstractObservationHandler {

    private static final String METER_NAME_SUFFIX = MeterSuffixes.TIMER;
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
        Timer.Sample sample = context.getRequired(Timer.Sample.class);
        timerRegistry(context, sample, getTags(context));
    }

    private void timerRegistry(Observation.Context context, Timer.Sample sample, List<Tag> tags) {
        sample.stop(Timer.builder(concat(context.getName(), METER_NAME_SUFFIX))
                .tags(tags).register(this.meterRegistry));
    }

    @Override
    public ObserveType observeType() {
        return ObserveType.TIMER;
    }

}
