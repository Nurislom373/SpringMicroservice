package og.khasanof.grafana.factories.resource;

import jakarta.servlet.http.HttpServletRequest;
import og.khasanof.grafana.models.resource.DefaultResource;
import og.khasanof.grafana.models.resource.Resource;
import og.khasanof.grafana.utils.GlobalConstants;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

/**
 * @author Nurislom
 * @see og.khasanof.grafana.factories.resource
 * @since 12/15/2023 4:58 PM
 */
@Component
public class DefaultResourceFactory implements ResourceFactory {

    @Override
    public Resource create(HttpServletRequest request) {
        return DefaultResource.builder()
                .uri(request.getRequestURI())
                .name(request.getRequestURI())
                .method(HttpMethod.valueOf(request.getMethod()))
                .observeTypes(GlobalConstants.DEFAULT_OBSERVE_TYPES)
                .build();
    }

}
