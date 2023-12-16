package og.khasanof.grafana.handler;

import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.Tag;
import io.micrometer.observation.Observation;
import io.micrometer.prometheus.PrometheusMeterRegistry;
import og.khasanof.grafana.AbstractObservationHandler;
import og.khasanof.grafana.enumeration.ObserveType;
import og.khasanof.grafana.factories.request.LastRequestStartTimeFactory;
import og.khasanof.grafana.models.request.LastRequestStartTime;
import og.khasanof.grafana.utils.MeterSuffixes;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;

import static og.khasanof.grafana.utils.BaseUtils.betweenMillisSeconds;
import static og.khasanof.grafana.utils.BaseUtils.concat;

/**
 * @author Nurislom
 * @see og.khasanof.grafana.handler
 * @since 12/15/2023 2:36 PM
 */
@Component
public class LastRequestMeterObservationHandler extends AbstractObservationHandler {

    private static final String METER_SUFFIX = MeterSuffixes.LAST_REQUEST;
    private final PrometheusMeterRegistry meterRegistry;
    private final LastRequestStartTimeFactory startTimeFactory;

    public LastRequestMeterObservationHandler(PrometheusMeterRegistry meterRegistry, LastRequestStartTimeFactory startTimeFactory) {
        this.meterRegistry = meterRegistry;
        this.startTimeFactory = startTimeFactory;
    }

    @Override
    public void onStart(Observation.Context context) {
        LastRequestStartTime startTime = startTimeFactory.create(Instant.now());
        context.put(LastRequestStartTime.class, startTime);
    }

    @Override
    public void onStop(Observation.Context context) {
        Instant endTime = Instant.now();
        LastRequestStartTime startTime = context.getRequired(LastRequestStartTime.class);
        gaugeRegistry(context, startTime.getStartTime(), endTime, getTags(context));
    }

    private void gaugeRegistry(Observation.Context context, Instant startTime, Instant endTime, List<Tag> tags) {
        Gauge.builder(concat(context.getName(), METER_SUFFIX), () -> betweenMillisSeconds(startTime, endTime))
                .tags(tags).register(meterRegistry);
    }

    @Override
    public ObserveType observeType() {
        return ObserveType.LAST_REQUEST;
    }

}
