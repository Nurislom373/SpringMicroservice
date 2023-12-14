package og.khasanof.grafana;

import io.micrometer.observation.Observation;
import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

/**
 * @author Nurislom
 * @see og.khasanof.grafana
 * @since 12/14/2023 4:44 PM
 */
@Data
@Component
@RequestScope
public class RequestContext {

    private Observation.Context context;

}
