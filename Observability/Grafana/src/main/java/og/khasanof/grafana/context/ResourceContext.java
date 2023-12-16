package og.khasanof.grafana.context;

import og.khasanof.grafana.IObservationHandler;
import og.khasanof.grafana.enumeration.ObserveType;

/**
 * @author Nurislom
 * @see og.khasanof.grafana.context
 * @since 12/16/2023 11:58 AM
 */
public interface ResourceContext {

    IObservationHandler get(ObserveType key);

}
