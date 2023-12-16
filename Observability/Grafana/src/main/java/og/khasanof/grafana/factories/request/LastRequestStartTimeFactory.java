package og.khasanof.grafana.factories.request;

import og.khasanof.grafana.models.request.LastRequestStartTime;

import java.time.Instant;

/**
 * @author Nurislom
 * @see og.khasanof.grafana
 * @since 12/15/2023 2:42 PM
 */
public interface LastRequestStartTimeFactory {

    LastRequestStartTime create(Instant startTime);

}
