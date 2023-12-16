package og.khasanof.grafana.factories.handler;

import og.khasanof.grafana.AbstractObservationHandler;
import og.khasanof.grafana.IObservationHandler;
import og.khasanof.grafana.context.ResourceContext;
import og.khasanof.grafana.enumeration.ObserveType;
import og.khasanof.grafana.models.resource.Resource;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.Set;

/**
 * @author Nurislom
 * @see og.khasanof.grafana.factories.handler
 * @since 12/16/2023 11:50 AM
 */
@Component
public class DefaultObservationHandlerFactory implements ObservationHandlerFactory {

    private final ResourceContext context;

    public DefaultObservationHandlerFactory(ResourceContext context) {
        this.context = context;
    }

    @Override
    public IObservationHandler create(Resource resource) {
        return createHandler(resource.getObserveTypes());
    }

    private IObservationHandler createHandler(Set<ObserveType> types) {
        Iterator<ObserveType> iterator = types.iterator();
        AbstractObservationHandler observationHandler = (AbstractObservationHandler) context.get(iterator.next());
        setNextHandlers(iterator, observationHandler);
        return observationHandler;
    }

    private void setNextHandlers(Iterator<ObserveType> iterator, AbstractObservationHandler nextHandler) {
        while (iterator.hasNext()) {
            AbstractObservationHandler handler = (AbstractObservationHandler) context.get(iterator.next());
            nextHandler.next(handler);
            nextHandler = handler;
        }
    }

}
