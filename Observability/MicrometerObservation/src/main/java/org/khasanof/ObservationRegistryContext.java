package org.khasanof;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;
import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationRegistry;

/**
 * @author Nurislom
 * @see org.khasanof
 * @since 12/8/2023 7:14 PM
 */
public class ObservationRegistryContext {

    private static final ObservationRegistry registry;
    private static final MeterRegistry meterRegistry;

    static {
        registry = ObservationRegistry.create();
        meterRegistry = new SimpleMeterRegistry();
        ObservationRegistry.ObservationConfig config = registry.observationConfig();
        config.observationHandler(new SimpleHandler(meterRegistry));
    }

    public static ObservationRegistry getRegistry() {
        return registry;
    }

    public static MeterRegistry getMeterRegistry() {
        return meterRegistry;
    }
    public static Observation createObservation() {
        return Observation.createNotStarted("foo", registry)
                .lowCardinalityKeyValue("lowTag", "lowTagValue")
                .highCardinalityKeyValue("highTag", "highTagValue");
    }


}
