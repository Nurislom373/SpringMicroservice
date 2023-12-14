package og.khasanof.grafana.resource;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpMethod;

/**
 * @author Nurislom
 * @see og.khasanof.grafana.resource
 * @since 12/14/2023 6:22 PM
 */
@Data
@AllArgsConstructor
public class DefaultResource implements Resource {

    private String name;
    private String uri;
    private HttpMethod method;

}
