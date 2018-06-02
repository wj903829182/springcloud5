package com.jack.webflux_websocket.handler;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * create by jack 2018/6/2
 * websocket 处理类
 */
@Component
public class EchoHandler implements WebSocketHandler {
    @Override
    public List<String> getSubProtocols() {
        return null;
    }

    /**
     * WebSocketHandler 接口，实现该接口来处理 WebSokcet 消息。
     handle(WebSocketSession session) 方法，接收 WebSocketSession 对象，
     即获取客户端信息、发送消息和接收消息的操作对象。
     receive() 方法，接收消息，使用 map 操作获取的 Flux 中包含的消息持续处理，
     并拼接出返回消息 Flux 对象。
     send() 方法，发送消息。消息为“服务端返回：jack， -> ”开头的。
     * @param webSocketSession
     * @return
     */
    @Override
    public Mono<Void> handle(WebSocketSession webSocketSession) {
        return webSocketSession.send(webSocketSession.receive().
                map(msg -> webSocketSession
                        .textMessage("服务端返回：jack ->" + msg.getPayloadAsText())));
    }
}
