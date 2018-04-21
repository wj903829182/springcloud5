package com.jack.webflux1.router;

import com.jack.webflux1.handler.HelloWorldHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

/**
 * create by jack 2018/4/21
 * 配置路由
 */
@Configuration
public class HelloWorldRouter {

    /**
     * RouterFunctions 对请求路由处理类，即将请求路由到处理器，这里将一个 GET 请求 /helloWorld 路由到处理器
     * cityHandler 的 helloCity 方法上。跟 Spring MVC 模式下的 HandleMapping 的作用类似。
     RouterFunctions.route(RequestPredicate, HandlerFunction) 方法，
     对应的入参是请求参数和处理函数，如果请求匹配，就调用对应的处理器函数。
     * @param helloWorldHandler
     * @return
     */
    @Bean
    public RouterFunction<ServerResponse> routeHelloWorld(HelloWorldHandler helloWorldHandler) {
        return RouterFunctions
                .route(RequestPredicates.GET("/helloWorld")
                                .and(RequestPredicates.accept(MediaType.TEXT_PLAIN)),
                        helloWorldHandler::helloWorld);
    }
}
