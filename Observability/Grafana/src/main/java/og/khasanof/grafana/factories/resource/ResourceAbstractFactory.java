package og.khasanof.grafana.factories.resource;

/**
 * @author Nurislom
 * @see og.khasanof.grafana.factories.resource
 * @since 12/15/2023 4:56 PM
 */
public interface ResourceAbstractFactory {

    ResourceFactory resourceFactory();

    ObserveResourceFactory nameResourceFactory();

}
