package org.example.reactornetty;

import reactor.netty.DisposableServer;
import reactor.netty.tcp.TcpServer;

public class Application {

    public static void main(String[] args) {
        DisposableServer server =
                TcpServer.create()
                        .host("localhost")
                        .port(8080)
                        .bindNow();

        server.onDispose()
                .block();
    }
}