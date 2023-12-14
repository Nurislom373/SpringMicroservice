package og.khasanof.grafana;

import io.micrometer.common.KeyValue;
import io.micrometer.core.instrument.Tag;
import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Nurislom
 * @see og.khasanof.grafana
 * @since 12/14/2023 4:27 PM
 */

public abstract class AbstractObservationHandler implements ObservationHandler<Observation.Context> {

    protected final String getErrorValue(Observation.Context context) {
        Throwable error = context.getError();
        return error != null ? error.getClass().getSimpleName() : "none";
    }

    protected final List<Tag> createTags(Observation.Context context) {
        List<Tag> tags = new ArrayList<>();
        for (KeyValue keyValue : context.getLowCardinalityKeyValues()) {
            tags.add(Tag.of(keyValue.getKey(), keyValue.getValue()));
        }
        return tags;
    }

}
