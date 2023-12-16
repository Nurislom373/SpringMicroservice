package og.khasanof.grafana.factories.request;

import og.khasanof.grafana.models.request.DefaultLastRequestStartTime;
import og.khasanof.grafana.models.request.LastRequestStartTime;
import org.springframework.stereotype.Component;

import java.time.Instant;

/**
 * @author Nurislom
 * @see og.khasanof.grafana
 * @since 12/15/2023 2:42 PM
 */
@Component
public class DefaultLastRequestStartTimeFactory implements LastRequestStartTimeFactory {

    @Override
    public LastRequestStartTime create(Instant startTime) {
        return new DefaultLastRequestStartTime(startTime);
    }

}
