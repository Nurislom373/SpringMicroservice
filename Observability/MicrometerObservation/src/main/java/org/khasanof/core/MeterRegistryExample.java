package org.khasanof.core;

import com.netflix.spectator.atlas.AtlasConfig;
import io.micrometer.atlas.AtlasMeterRegistry;
import io.micrometer.atlas.AtlasNamingConvention;
import io.micrometer.core.instrument.Clock;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.composite.CompositeMeterRegistry;
import io.micrometer.core.instrument.config.NamingConvention;
import io.micrometer.core.instrument.logging.LoggingMeterRegistry;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;

import java.time.Duration;

/**
 * In Micrometer, a MeterRegistry is the core component used for registering meters. We can iterate over the registry
 * and further each meter’s metrics to generate a time series in the backend with combinations of metrics and their
 * dimension values.
 *
 * @author Nurislom
 * @see org.khasanof.core
 * @since 12/10/2023 4:33 PM
 */
public class MeterRegistryExample {

    public static void main(String[] args) {
        meterRegistryExample2();
    }

    static void meterRegistryExample1() {
        SimpleMeterRegistry simpleMeterRegistry = new SimpleMeterRegistry();
        CompositeMeterRegistry compositeMeterRegistry = new CompositeMeterRegistry();
        LoggingMeterRegistry registry = new LoggingMeterRegistry();

        AtlasConfig atlasConfig = new AtlasConfig() {
            @Override
            public Duration step() {
                return Duration.ofSeconds(10);
            }

            @Override
            public String get(String k) {
                return null; // accept the rest of the defaults
            }
        };
        AtlasMeterRegistry atlasMeterRegistry = new AtlasMeterRegistry(atlasConfig, Clock.SYSTEM);
    }

    static void meterRegistryExample2() {
        CompositeMeterRegistry composite = new CompositeMeterRegistry();

        Counter compositeCounter = composite.counter("counter");
        compositeCounter.increment();

        SimpleMeterRegistry simple = new SimpleMeterRegistry();
        composite.add(simple);

        compositeCounter.increment();
        compositeCounter.increment();

        System.out.println("composite = " + composite);
        System.out.println("compositeCounter.count() = " + compositeCounter.count());
        compositeCounter.measure().forEach(System.out::println);
    }


    /*
        Naming Convention

        1. Prometheus - http_server_requests_duration_seconds
        2. Atlas - httpServerRequests
        3. Graphite - http.server.requests
        4. InfluxDB - http_server_requests
     */
    static void meterRegistryExample3(MeterRegistry registry) {
        NamingConvention namingConvention = new AtlasNamingConvention();
        registry.config().namingConvention(namingConvention);
    }

    /*
        Suppose we are trying to measure the number of http requests and the number of database calls.

     */
    static void tagNaming(MeterRegistry registry) {
        // Recommended Approach
        registry.counter("database.calls", "db", "users");
        registry.counter("http.requests", "uri", "/api/users");

        // Bad Approach
        registry.counter("calls", "class", "database", "db", "users");

        registry.counter("calls", "class", "http", "uri", "/api/users");
    }

}
