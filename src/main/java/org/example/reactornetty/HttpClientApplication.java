package org.example.reactornetty;

import io.netty.handler.logging.LogLevel;
import reactor.core.publisher.Mono;
import reactor.netty.ByteBufFlux;
import reactor.netty.http.client.HttpClient;
import reactor.netty.transport.logging.AdvancedByteBufFormat;

public class HttpClientApplication {

    public static void main(String[] args) {

        HttpClient client = HttpClient.create()
                .wiretap("reactor.netty.http.client", LogLevel.DEBUG, AdvancedByteBufFormat.TEXTUAL);


        client.get()
                .uri("localhost:8080/hello")
                .response()
                .doOnNext(res -> System.out.println("Response: " + res))
                .block();

        client.post()
                .uri("localhost:8080/echo")
                .send(ByteBufFlux.fromString(Mono.just("hello")))
                .response()
                .doOnNext(res -> System.out.println("Response: " + res))
                .block();

    }
}