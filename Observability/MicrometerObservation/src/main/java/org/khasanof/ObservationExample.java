package org.khasanof;

import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.Meter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import io.micrometer.observation.Observation;

import java.util.List;


/**
 * @author Nurislom
 * @see org.khasanof
 * @since 12/8/2023 7:10 PM
 */
public class ObservationExample {

    public static void main(String[] args) {
        Observation observation = ObservationRegistryContext.createObservation();
        Observation start = observation.start();
        start.observe(() -> doSomeWork(ObservationRegistryContext.getMeterRegistry()));
        start.observe(() -> doSomeWork(ObservationRegistryContext.getMeterRegistry()));

        System.out.println("observation = " + observation);
        System.out.println("observation.getClass = " + observation.getClass());
        System.out.println("observation.getContextView() = " + observation.getContextView());

        MeterRegistry meterRegistry = ObservationRegistryContext.getMeterRegistry();
        System.out.println("meterRegistry = " + meterRegistry);
        List<Meter> meters = meterRegistry.getMeters();
        meters.forEach(meter -> {
            System.out.println("meter.getId() = " + meter.getId());
            meter.measure().forEach(System.out::println);
            System.out.println(" ");
        });
    }

    static void doSomeWork(MeterRegistry registry) {
        Timer.Sample sample = Timer.start(registry);
        try {
            Gauge.builder("cache.size", () -> 10L)
                    .register(registry);

            registry.gauge("test.size", 15);
            // do some work here
            Thread.sleep(100L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            sample.stop(Timer.builder("my.timer").register(registry));
        }
    }

}
