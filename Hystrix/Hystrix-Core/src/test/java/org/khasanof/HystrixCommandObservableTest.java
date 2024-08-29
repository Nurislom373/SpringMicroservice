package org.khasanof;

import org.junit.jupiter.api.Test;
import rx.Observable;
import rx.Observer;

import java.util.concurrent.ExecutionException;

/**
 * @author Nurislom
 * @see org.khasanof
 * @since 5/27/2024 10:03 AM
 */
public class HystrixCommandObservableTest {

    /**
     *
     */
    @Test
    void observableExecutionShouldSuccess() throws ExecutionException, InterruptedException {
        Observable<String> khasanofObserve = new CommandHelloWorldObservable("Khasanof").observe();
        Observable<String> abdullohObserve = new CommandHelloWorldObservable("Abdulloh").observe();

        // non-blocking
        // - this is a verbose anonymous inner-class approach and doesn't do assertions
        khasanofObserve.subscribe(new Observer<>() {
            @Override
            public void onCompleted() {
                System.out.println("Completed");
            }

            @Override
            public void onError(Throwable throwable) {
                throwable.printStackTrace();
            }

            @Override
            public void onNext(String s) {
                System.out.println("onNext: = " + s);
            }
        });

        // non-blocking
        // - also verbose anonymous inner-class
        // - ignore errors and onCompleted signal
        abdullohObserve.subscribe(s -> System.out.println("onNext: " + s));
    }
}
