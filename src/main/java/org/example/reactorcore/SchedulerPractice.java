package org.example.reactorcore;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

public class SchedulerPractice {
    public static void main(String[] args) {

        Scheduler s = Schedulers.newParallel("parallel-scheduler", 4);
        Flux<String> flux = Flux.range(1, 2)
                .map(i -> 10 + i)  // 현재 스레드에서 실행
                .publishOn(s)       // 이후 연산자들을 병렬 스케줄러에서 실행
                .map(i -> "["+Thread.currentThread().getName()+"] value " + i);

        Thread thread = new Thread(() -> flux.subscribe(System.out::println));

        thread.start();
    }
}
