package og.khasanof.grafana;

import io.micrometer.core.instrument.Tag;
import io.micrometer.observation.Observation;

import java.util.List;
import java.util.Objects;

import static og.khasanof.grafana.utils.BaseUtils.createTags;
import static og.khasanof.grafana.utils.BaseUtils.getErrorValue;

/**
 * @author Nurislom
 * @see og.khasanof.grafana
 * @since 12/14/2023 4:27 PM
 */
public abstract class AbstractObservationHandler implements IObservationHandler {

    private AbstractObservationHandler nextObservationHandler;

    public AbstractObservationHandler() {
    }

    public AbstractObservationHandler(AbstractObservationHandler nextObservationHandler) {
        this.nextObservationHandler = nextObservationHandler;
    }

    public AbstractObservationHandler next(AbstractObservationHandler nextObservationHandler) {
        this.nextObservationHandler = nextObservationHandler;
        return this;
    }

    @Override
    public void onStart(Observation.Context context, boolean moveToNext) {
        onStart(context);
        nextObservationHandlerOnStart(context, moveToNext);
    }

    @Override
    public void onStop(Observation.Context context, boolean moveToNext) {
        onStop(context);
        nextObservationHandlerOnStop(context, moveToNext);
    }

    private void nextObservationHandlerOnStart(Observation.Context context, boolean moveToNext) {
        nextObservationHandlerOnRunnable(moveToNext, () -> nextObservationHandlerOnStart(context, moveToNext));
    }

    private void nextObservationHandlerOnStop(Observation.Context context, boolean moveToNext) {
        nextObservationHandlerOnRunnable(moveToNext, () -> nextObservationHandlerOnStop(context, moveToNext));
    }

    private void nextObservationHandlerOnRunnable(boolean moveToNext, Runnable runnable) {
        if (Objects.nonNull(nextObservationHandler) && moveToNext) {
            runnable.run();
        }
    }

    protected final List<Tag> getTags(Observation.Context context) {
        List<Tag> tags = createTags(context);
        tags.add(Tag.of("error", getErrorValue(context)));
        return tags;
    }

}
