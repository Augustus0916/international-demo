package ljh.augustus.demogateway.filter;

import ljh.augustus.demokafka.producer.GatewayProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Configuration
@Slf4j
public class TokenFilter implements GlobalFilter {

    @Autowired
    private GatewayProducer gatewayProducer;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //token（用户身份）判断 所有新添加的请求头都会翻译成小写
        String headerName = "session";
        String headerValue = UUID.randomUUID().toString().replaceAll("-", "");

        gatewayProducer.sendMsgToTopic1(headerValue);
        gatewayProducer.sendMsgToTopic2(headerValue);

        ServerHttpRequest req = exchange.getRequest();
        req.mutate().header(headerName, headerValue).build();

        return chain.filter(exchange.mutate().request(req.mutate().build()).build());
    }

}
