package og.khasanof.grafana;

import io.micrometer.observation.Observation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import og.khasanof.grafana.factories.context.ObservationContextFactory;
import og.khasanof.grafana.factories.handler.ObservationHandlerFactory;
import og.khasanof.grafana.service.checker.HttpServletRequestChecker;
import og.khasanof.grafana.service.registry.RestRegistryManager;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Nurislom
 * @see og.khasanof.grafana
 * @since 12/14/2023 3:31 PM
 */
@Slf4j
@Component
public class DefaultRestInterceptor extends AbstractRestInterceptor {

    protected DefaultRestInterceptor(ObservationHandlerFactory observationHandlerFactory, HttpServletRequestChecker httpServletRequestChecker,
                                     ObservationContextFactory observationContextFactory, RestRegistryManager restRegistryManager) {
        super(observationHandlerFactory, httpServletRequestChecker, observationContextFactory, restRegistryManager);
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        observation(request);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

}
