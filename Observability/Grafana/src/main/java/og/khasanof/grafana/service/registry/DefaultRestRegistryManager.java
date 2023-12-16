package og.khasanof.grafana.service.registry;

import og.khasanof.grafana.models.resource.Resource;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.Predicate;

/**
 * @author Nurislom
 * @see og.khasanof.grafana
 * @since 12/14/2023 6:25 PM
 */
@Component
public class DefaultRestRegistryManager implements RestRegistryManager {

    private final Set<Resource> resources = new HashSet<>();

    @Override
    public void add(Resource resource) {
        resources.add(resource);
    }

    @Override
    public void remove(Resource resource) {
        resources.remove(resource);
    }

    @Override
    public Optional<Resource> find(String name) {
        return findBy(name);
    }

    @Override
    public Optional<Resource> find(String uri, HttpMethod method) {
        return findBy(uri, method);
    }

    @Override
    public boolean contains(String name) {
        return findBy(name).isPresent();
    }

    @Override
    public boolean contains(String uri, HttpMethod method) {
        return findBy(uri, method).isPresent();
    }

    @Override
    public Set<Resource> getAll() {
        return this.resources;
    }

    public Optional<Resource> findBy(Predicate<Resource> predicate) {
        return this.resources.stream().filter(predicate).findFirst();
    }

    private Optional<Resource> findBy(String name) {
        return findBy(resource -> equals(name, resource));
    }

    private Optional<Resource> findBy(String uri, HttpMethod method) {
        return findBy(resource -> equals(uri, method, resource));
    }

    private boolean equals(String uri, HttpMethod method, Resource resource) {
        return Objects.equals(uri, resource.getUri()) && Objects.equals(method, resource.getMethod());
    }

    private boolean equals(String name, Resource resource) {
        return Objects.equals(name, resource.getName());
    }

}
