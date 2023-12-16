package og.khasanof.grafana.models.resource;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import og.khasanof.grafana.enumeration.ObserveType;
import org.springframework.http.HttpMethod;

import java.util.Set;

/**
 * @author Nurislom
 * @see og.khasanof.grafana
 * @since 12/14/2023 6:22 PM
 */
@Data
@Builder
@AllArgsConstructor
public class DefaultResource implements Resource {

    private String name;
    private String uri;
    private HttpMethod method;
    private Set<ObserveType> observeTypes;

}
