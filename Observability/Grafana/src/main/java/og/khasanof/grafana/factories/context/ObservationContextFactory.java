package og.khasanof.grafana.factories.context;

import io.micrometer.observation.Observation;
import og.khasanof.grafana.factories.GenericFactory;

/**
 * @author Nurislom
 * @see og.khasanof.grafana.factories.context
 * @since 12/16/2023 12:17 PM
 */
public interface ObservationContextFactory extends GenericFactory<String, Observation.Context> {
}
