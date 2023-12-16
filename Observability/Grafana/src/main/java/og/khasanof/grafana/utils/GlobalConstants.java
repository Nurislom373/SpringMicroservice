package og.khasanof.grafana.utils;

import og.khasanof.grafana.enumeration.ObserveType;

import java.util.List;
import java.util.Set;

/**
 * @author Nurislom
 * @see og.khasanof.grafana.utils
 * @since 12/15/2023 6:02 PM
 */
public abstract class GlobalConstants {

    public static final Set<ObserveType> DEFAULT_OBSERVE_TYPES = Set.of(ObserveType.TIMER);
    public static final String CONTEXT_NAME = "context";

}
