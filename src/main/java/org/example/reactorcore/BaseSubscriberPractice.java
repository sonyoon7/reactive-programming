package org.example.reactorcore;

import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;

public class BaseSubscriberPractice {

    public static void main(String[] args) throws InterruptedException {
        Flux<Integer> flux = Flux.range(1, 10);

        CustomSubscriber<Integer> subscriber = new CustomSubscriber<>();
        flux.subscribe(subscriber); // 구독 시작

        Thread.sleep(1000);
        System.out.println("🔹 외부에서 데이터 3개 요청");
        subscriber.request(3);

        Thread.sleep(2000);
        System.out.println("🔹 외부에서 구독 취소!");
        subscriber.cancel(); // 외부에서 구독 취소
    }

    static class CustomSubscriber<T> extends BaseSubscriber<T> {
        @Override
        protected void hookOnSubscribe(org.reactivestreams.Subscription subscription) {
            System.out.println("✅ 구독 시작 (아직 데이터 요청 안 함)");
        }

        @Override
        protected void hookOnNext(T value) {
            System.out.println("받은 데이터: " + value);
        }

        @Override
        protected void hookOnCancel() {
            System.out.println("🚨 구독이 외부에서 취소되었습니다!");
        }
    }
}

