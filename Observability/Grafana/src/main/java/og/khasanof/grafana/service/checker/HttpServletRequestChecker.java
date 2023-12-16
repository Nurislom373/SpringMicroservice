package og.khasanof.grafana.service.checker;

import jakarta.servlet.http.HttpServletRequest;

/**
 * @author Nurislom
 * @see og.khasanof.grafana
 * @since 12/15/2023 5:45 PM
 */
public interface HttpServletRequestChecker {

    boolean isRegistry(HttpServletRequest request);

}
