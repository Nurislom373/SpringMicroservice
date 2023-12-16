package og.khasanof.grafana;

import io.micrometer.observation.Observation;
import jakarta.servlet.http.HttpServletRequest;
import og.khasanof.grafana.factories.context.ObservationContextFactory;
import og.khasanof.grafana.factories.handler.ObservationHandlerFactory;
import og.khasanof.grafana.models.resource.Resource;
import og.khasanof.grafana.service.checker.HttpServletRequestChecker;
import og.khasanof.grafana.service.registry.RestRegistryManager;
import org.springframework.http.HttpMethod;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.HandlerInterceptor;

import static og.khasanof.grafana.utils.GlobalConstants.CONTEXT_NAME;

/**
 * @author Nurislom
 * @see og.khasanof.grafana
 * @since 12/15/2023 5:48 PM
 */
public abstract class AbstractRestInterceptor implements HandlerInterceptor {

    private final ObservationHandlerFactory observationHandlerFactory;
    private final HttpServletRequestChecker httpServletRequestChecker;
    private final ObservationContextFactory observationContextFactory;
    private final RestRegistryManager restRegistryManager;

    protected AbstractRestInterceptor(ObservationHandlerFactory observationHandlerFactory, HttpServletRequestChecker httpServletRequestChecker,
                                      ObservationContextFactory observationContextFactory, RestRegistryManager restRegistryManager) {
        this.observationHandlerFactory = observationHandlerFactory;
        this.httpServletRequestChecker = httpServletRequestChecker;
        this.observationContextFactory = observationContextFactory;
        this.restRegistryManager = restRegistryManager;
    }

    protected void observation(HttpServletRequest request) {
        if (httpServletRequestChecker.isRegistry(request)) {
            findResource(request);
        }
    }

    private void findResource(HttpServletRequest request) {
        restRegistryManager.find(request.getRequestURI(), HttpMethod.valueOf(request.getMethod()))
                .ifPresent(resource -> observationStart(request, resource));
    }

    private void observationStart(HttpServletRequest request, Resource resource) {
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        IObservationHandler observationHandler = observationHandlerFactory.create(resource);
        Observation.Context context = observationContextFactory.create(request.getRequestURI());
        requestAttributes.setAttribute(CONTEXT_NAME, context, RequestAttributes.SCOPE_REQUEST);
        observationHandler.onStart(context, true);
    }

}
