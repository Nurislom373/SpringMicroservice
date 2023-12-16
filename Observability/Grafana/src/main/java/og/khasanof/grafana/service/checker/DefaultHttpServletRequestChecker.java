package og.khasanof.grafana.service.checker;

import jakarta.servlet.http.HttpServletRequest;
import og.khasanof.grafana.service.registry.RestRegistryManager;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

/**
 * @author Nurislom
 * @see og.khasanof.grafana
 * @since 12/15/2023 5:45 PM
 */
@Service
public class DefaultHttpServletRequestChecker implements HttpServletRequestChecker {

    private final RestRegistryManager registryManager;

    public DefaultHttpServletRequestChecker(RestRegistryManager registryManager) {
        this.registryManager = registryManager;
    }

    @Override
    public boolean isRegistry(HttpServletRequest request) {
        return tryIsRegistry(request);
    }

    private boolean tryIsRegistry(HttpServletRequest request) {
        return registryManager.contains(request.getRequestURI(), HttpMethod.valueOf(request.getMethod()));
    }

}
