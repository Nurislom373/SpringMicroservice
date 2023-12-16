package og.khasanof.grafana.utils;

import io.micrometer.common.KeyValue;
import io.micrometer.core.instrument.Tag;
import io.micrometer.observation.Observation;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Nurislom
 * @see og.khasanof.grafana.utils
 * @since 12/15/2023 3:02 PM
 */
public abstract class BaseUtils {

    public static String concat(String var1, String var2) {
        return var1.concat(var2);
    }

    public static long betweenMillisSeconds(Instant startTime, Instant endTime) {
        return Duration.between(startTime, endTime).toMillis();
    }

    public static String getErrorValue(Observation.Context context) {
        Throwable error = context.getError();
        return error != null ? error.getClass().getSimpleName() : "none";
    }

    public static List<Tag> createTags(Observation.Context context) {
        List<Tag> tags = new ArrayList<>();
        for (KeyValue keyValue : context.getLowCardinalityKeyValues()) {
            tags.add(Tag.of(keyValue.getKey(), keyValue.getValue()));
        }
        return tags;
    }

}
