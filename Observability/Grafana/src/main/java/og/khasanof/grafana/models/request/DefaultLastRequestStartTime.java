package og.khasanof.grafana.models.request;

import lombok.*;

import java.time.Instant;

/**
 * @author Nurislom
 * @see og.khasanof.grafana.models
 * @since 12/15/2023 4:12 PM
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DefaultLastRequestStartTime implements LastRequestStartTime {

    private Instant startTime;

}
