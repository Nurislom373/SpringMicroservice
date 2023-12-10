package org.khasanof.core;

import io.micrometer.core.instrument.Meter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.config.MeterFilter;
import io.micrometer.core.instrument.config.MeterFilterReply;

/**
 * @author Nurislom
 * @see org.khasanof.core
 * @since 12/10/2023 6:15 PM
 */
public class MeterRegistryFilter {

    static void filterExample1(MeterRegistry registry) {
        registry.config()
                .meterFilter(MeterFilter.ignoreTags("too.much.information"))
                .meterFilter(MeterFilter.denyNameStartsWith("jvm"));
    }

    static MeterFilter customFilter() {
        return new MeterFilter() {
            @Override
            public MeterFilterReply accept(Meter.Id id) {
                if (id.getName().contains("test")) {
                    return MeterFilterReply.DENY;
                }
                return MeterFilter.super.accept(id);
            }
        };
    }

}
