package org.khasanof.core;

import com.netflix.spectator.atlas.AtlasConfig;
import io.micrometer.atlas.AtlasMeterRegistry;
import io.micrometer.core.instrument.Clock;
import io.micrometer.core.instrument.composite.CompositeMeterRegistry;
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
    
    public void meterRegistry() {
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
    
}
