package og.khasanof.grafana.factories.resource;

/**
 * @author Nurislom
 * @see og.khasanof.grafana.factories.resource
 * @since 12/15/2023 5:32 PM
 */
public class DefaultResourceAbstractFactory implements ResourceAbstractFactory {

    @Override
    public ResourceFactory resourceFactory() {
        return new DefaultResourceFactory();
    }

    @Override
    public ObserveResourceFactory nameResourceFactory() {
        return new DefaultObserveResourceFactory();
    }

}
