package og.khasanof.grafana;

import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationHandler;
import og.khasanof.grafana.enumeration.ObserveType;

/**
 * @author Nurislom
 * @see og.khasanof.grafana
 * @since 12/16/2023 11:41 AM
 */
public interface IObservationHandler extends ObservationHandler<Observation.Context> {

    void onStart(Observation.Context context, boolean moveToNext);

    void onStop(Observation.Context context, boolean moveToNext);

    ObserveType observeType();

    @Override
    default boolean supportsContext(Observation.Context context) {
        return true;
    }

}
