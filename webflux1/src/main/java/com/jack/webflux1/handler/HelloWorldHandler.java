package com.jack.webflux1.handler;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * create by jack 2018/4/21
 */
@Component
public class HelloWorldHandler {
    /**
     * ServerResponse 是对响应的封装，可以设置响应状态、响应头、响应正文。
     * 比如 ok 代表的是 200 响应码、MediaType 枚举是代表这文本内容类型、返回的是 String 的对象。
     这里用 Mono 作为返回对象，是因为返回包含了一个 ServerResponse 对象，而不是多个元素
     * @param request
     * @return
     */
    public Mono<ServerResponse> helloWorld(ServerRequest request) {
        return ServerResponse.ok().contentType(MediaType.TEXT_PLAIN)
                .body(BodyInserters.fromObject("Hello, World Flux!"));
    }
}
