package org.example.reactorcore;

import reactor.core.publisher.Flux;

public class SubscribePractice {
    public static void main(String[] args) throws InterruptedException {
//        Flux<Integer> ints = Flux.range(1, 3);
//        ints.subscribe();
//        ints.subscribe(System.out::println);

//
//        Flux<Integer> intsWithErrorHandler= Flux.range(1, 10)
//                .map(i -> {
//                    if (i <= 3) return i;
//                    throw new RuntimeException("Got to 4");
//                });
//        intsWithErrorHandler.subscribe(i -> System.out.println(i),
//                error -> System.err.println("Error: " + error));

        Flux<Integer> intsWithCompleteHandler = Flux.range(1, 4);
        intsWithCompleteHandler.subscribe(i -> System.out.println(i),
                error -> System.err.println("Error " + error),
                () -> System.out.println("Done"));
    }


}