package cn.pxy.gateway;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author pxy
 * @software IntelliJ IDEA
 * @create 2023-04-03 19:22
 **/

@Order(-1)
@Component
public class AuthorizeFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //1.获取请求参数
        ServerHttpRequest request = exchange.getRequest();
        MultiValueMap<String, String> params = request.getQueryParams();
        //2.获取参数中的authorization 参数
        String authorization = params.getFirst("authorization");
        //3.判断参数是否等于 admin
        if ("admin".equals(authorization)) {
            //4.是,放行
            return chain.filter(exchange);
        }
        //5.不是,返回
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        return exchange.getResponse().setComplete();
    }

    /**
     * 过滤器执行的顺序，值越小，优先级越高
     * @return -1
     */
    @Override
    public int getOrder() {
        return -1;
    }
}
