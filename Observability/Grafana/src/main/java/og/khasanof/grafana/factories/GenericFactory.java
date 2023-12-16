package og.khasanof.grafana.factories;

/**
 * @author Nurislom
 * @see og.khasanof.grafana.factories
 * @since 12/15/2023 4:56 PM
 */
public interface GenericFactory<P, R> {

    R create(P p);

}
