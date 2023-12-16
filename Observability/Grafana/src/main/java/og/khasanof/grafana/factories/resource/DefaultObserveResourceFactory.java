package og.khasanof.grafana.factories.resource;

import og.khasanof.grafana.models.resource.DefaultResource;
import og.khasanof.grafana.models.resource.ObserveResource;
import og.khasanof.grafana.models.resource.Resource;
import org.springframework.http.HttpMethod;

/**
 * @author Nurislom
 * @see og.khasanof.grafana.factories.resource
 * @since 12/15/2023 5:21 PM
 */
public class DefaultObserveResourceFactory implements ObserveResourceFactory {

    @Override
    public Resource create(ObserveResource observeResource) {
        return DefaultResource.builder()
                .name(observeResource.getName())
                .observeTypes(observeResource.getObserveTypes())
                .uri(observeResource.getRequest().getRequestURI())
                .method(HttpMethod.valueOf(observeResource.getRequest().getMethod()))
                .build();
    }

}
