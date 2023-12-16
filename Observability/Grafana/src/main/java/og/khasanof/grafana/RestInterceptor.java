package og.khasanof.grafana;

import io.micrometer.observation.Observation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
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
public class RestInterceptor implements HandlerInterceptor {

    private final Map<String, Observation.Context> contextMap = new ConcurrentHashMap<>();
    private final AbstractObservationHandler abstractObservationHandler;

    public RestInterceptor(AbstractObservationHandler abstractObservationHandler) {
        this.abstractObservationHandler = abstractObservationHandler;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        if (!contextMap.containsKey(request.getRequestURI())) {
            contextMap.put(request.getRequestURI(), createContext(request.getRequestURI()));
        }
        Observation.Context context = contextMap.get(request.getRequestURI());
        abstractObservationHandler.onStart(context);
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if (contextMap.containsKey(request.getRequestURI())) {
            Observation.Context context = contextMap.get(request.getRequestURI());
            abstractObservationHandler.onStop(context);
        }
    }

    private Observation.Context createContext(String name) {

    }

}
