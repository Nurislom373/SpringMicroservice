package og.khasanof.grafana.models.resource;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import og.khasanof.grafana.enumeration.ObserveType;

import java.util.Set;

/**
 * @author Nurislom
 * @see og.khasanof.grafana.models.resource
 * @since 12/15/2023 5:24 PM
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ObserveResource {

    private String name;
    private HttpServletRequest request;
    private Set<ObserveType> observeTypes;

}
