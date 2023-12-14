package og.khasanof.grafana.registry;

import og.khasanof.grafana.resource.Resource;
import org.springframework.http.HttpMethod;

import java.util.Optional;

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

    void contains(String name);

    void contains(String uri, HttpMethod method);

}
