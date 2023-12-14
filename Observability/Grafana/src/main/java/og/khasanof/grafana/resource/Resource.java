package og.khasanof.grafana.resource;

import org.springframework.http.HttpMethod;

/**
 * @author Nurislom
 * @see og.khasanof.grafana
 * @since 12/14/2023 6:20 PM
 */

public interface Resource {

    String getName(); // resource name must be unique!

    String getUri();

    HttpMethod getMethod();

}
