package org.example.reactivestream;


import java.util.concurrent.Flow;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        OneShotPublisher publisher = new OneShotPublisher();

        // 구독자(Subscriber) 정의
        Flow.Subscriber<Boolean> subscriber = new Flow.Subscriber<>() {
            @Override
            public void onSubscribe(Flow.Subscription subscription) {
                System.out.println("구독 시작!");
                subscription.request(1); // TRUE 값 1개 요청
            }

            @Override
            public void onNext(Boolean item) {
                System.out.println("받은 데이터: " + item);
            }

            @Override
            public void onError(Throwable throwable) {
                System.err.println("에러 발생: " + throwable.getMessage());
            }

            @Override
            public void onComplete() {
                System.out.println("데이터 전송 완료!");
            }
        };


        // OneShotPublisher에 구독자 등록
        publisher.subscribe(subscriber);

        // 프로그램이 종료되지 않도록 대기 (비동기 작업이므로)
        Thread.sleep(5000);
    }


}