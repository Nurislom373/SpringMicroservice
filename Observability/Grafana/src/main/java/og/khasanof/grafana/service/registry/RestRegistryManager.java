package og.khasanof.grafana.service.registry;

import og.khasanof.grafana.models.resource.Resource;
import org.springframework.http.HttpMethod;

import java.util.Optional;
import java.util.Set;

/**
 * @author Nurislom
 * @see og.khasanof.grafana
 * @since 12/14/2023 6:20 PM
 */
public interface RestRegistryManager {

    void add(Resource resource);

    void remove(Resource resource);

    Optional<Resource> find(String name);

    Optional<Resource> find(String uri, HttpMethod method);

    boolean contains(String name);

    boolean contains(String uri, HttpMethod method);

    Set<Resource> getAll();
}
