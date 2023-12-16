package og.khasanof.grafana.factories.context;

import io.micrometer.observation.Observation;
import org.springframework.stereotype.Component;

/**
 * @author Nurislom
 * @see og.khasanof.grafana.factories.context
 * @since 12/16/2023 12:17 PM
 */
@Component
public class DefaultObservationContextFactory implements ObservationContextFactory {

    @Override
    public Observation.Context create(String name) {
        Observation.Context context = new Observation.Context();
        context.setName(name.replaceFirst("/", "").replaceAll("/", "_"));
        return context;
    }

}
