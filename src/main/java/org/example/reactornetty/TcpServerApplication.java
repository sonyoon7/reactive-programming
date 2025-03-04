package org.example.reactornetty;

import reactor.core.publisher.Mono;
import reactor.netty.DisposableServer;
import reactor.netty.tcp.TcpServer;
import reactor.netty.transport.logging.AdvancedByteBufFormat;

public class TcpServerApplication {

    public static void main(String[] args) {
        DisposableServer server =
                TcpServer.create()
                        .host("localhost")
                        .port(8080)
                        .doOnConnection(conn -> System.out.println("New Connection: " + conn.address()))
                        .doOnBind(serverConfig-> System.out.println("Server is starting..."))
                        .doOnUnbound(disposableServer ->  System.out.println("Server is stopping..."))
                        .wiretap("reactor.netty.tcp", io.netty.handler.logging.LogLevel.DEBUG, AdvancedByteBufFormat.TEXTUAL) // 로그 설정
                        .handle((inbound, outbound) ->
                                inbound.receive()
                                        .asString()
                                        .doOnNext(msg -> System.out.println("Received: " + msg))
                                        .flatMap(msg -> outbound.sendString(
                                                Mono.just("Echo: " + msg + "\n")))
                                        .then()
                        )
                        .bindNow();


        server.onDispose()
                .block();
    }
}