package com.hengcloud.heng.gateway.filter;

import com.hengcloud.heng.core.constant.SecurityConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;


import java.lang.annotation.Annotation;

@Slf4j
@Component
public class GateWayFilter implements GlobalFilter, Order {
    @Override
    public Class<? extends Annotation> annotationType() {

        return null;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        // 1. 清洗请求头中from 参数
        ServerHttpRequest request = exchange.getRequest().mutate()
                .headers(httpHeaders -> httpHeaders.remove(SecurityConstants.FROM)).build();
        String str = exchange.getRequest().getQueryParams().getFirst("user");
        if(!StringUtils.hasText(str)){

            exchange.getResponse().setStatusCode(HttpStatus.BAD_REQUEST);
           return exchange.getResponse().setComplete();
        }
        return chain.filter(exchange);
    }

    @Override
    public int value() {
        return 2;
    }
}
