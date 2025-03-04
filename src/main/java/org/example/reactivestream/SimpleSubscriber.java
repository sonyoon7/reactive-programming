package org.example.reactivestream;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public class SimpleSubscriber<T> implements Subscriber<T> {
    private final Integer count;

    public SimpleSubscriber(Integer count) {
        this.count = count;
    }

    @Override
    public void onSubscribe(Subscription s) {
        System.out.println("subscribe");
        s.request(count);
        System.out.println("request: "+ count);
    }

    @Override
    public void onNext(T t) {
        System.out.println("item:"+ t);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onError(Throwable t) {
        System.out.println("error: "+ t.getMessage());
    }

    @Override
    public void onComplete() {
        System.out.println("complete");
    }
}
