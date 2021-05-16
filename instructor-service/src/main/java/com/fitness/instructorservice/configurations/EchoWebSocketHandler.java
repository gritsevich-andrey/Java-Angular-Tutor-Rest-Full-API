//package com.fitness.instructorservice.configurations;
//
//import org.springframework.lang.NonNull;
//import org.springframework.web.reactive.socket.WebSocketHandler;
//import org.springframework.web.reactive.socket.WebSocketMessage;
//import org.springframework.web.reactive.socket.WebSocketSession;
//import reactor.core.publisher.Mono;
//
//import java.time.Duration;
//
//public class EchoWebSocketHandler implements WebSocketHandler {
//
//    @Override
//    @NonNull
//    public Mono<Void> handle(WebSocketSession session) {
//        return session.send(session.receive()
//                .doOnNext(WebSocketMessage::retain)
//                .delayElements(Duration.ofSeconds(1)).log());
//    }
//}
