package org.khasanof;

import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixObservableCommand;
import rx.Observable;
import rx.Subscriber;
import rx.internal.operators.OnSubscribeDelay;
import rx.schedulers.Schedulers;

/**
 * @author Nurislom
 * @see org.khasanof
 * @since 5/27/2024 9:51 AM
 */
public class CommandHelloWorldObservable extends HystrixObservableCommand<String> {

    private final String name;

    public CommandHelloWorldObservable(String name) {
        super(HystrixCommandGroupKey.Factory.asKey("ObservableExampleGroup"));
        this.name = name;
    }

    @Override
    protected Observable<String> construct() {
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> observer) {
                try {
                    if (!observer.isUnsubscribed()) {
                        // a real example would do work like a network call here
                        observer.onNext("Hello");
                        observer.onNext(name + "!");
                        observer.onCompleted();
                    }
                } catch (Exception e) {
                    observer.onError(e);
                }
            }
        }).subscribeOn(Schedulers.io());
    }
}
