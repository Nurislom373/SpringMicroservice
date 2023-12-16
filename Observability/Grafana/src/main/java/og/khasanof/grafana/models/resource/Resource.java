package og.khasanof.grafana.models.resource;

import og.khasanof.grafana.enumeration.ObserveType;
import org.springframework.http.HttpMethod;

import java.util.Set;

/**
 * @author Nurislom
 * @see og.khasanof.grafana
 * @since 12/14/2023 6:20 PM
 */
public interface Resource {

    String getName(); // resource name must be unique!

    String getUri();

    HttpMethod getMethod();

    Set<ObserveType> getObserveTypes();

}
