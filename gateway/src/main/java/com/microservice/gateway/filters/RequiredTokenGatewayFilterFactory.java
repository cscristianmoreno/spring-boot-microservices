package com.microservice.gateway.filters;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;

import com.google.common.net.HttpHeaders;
import com.microservice.gateway.dto.JwtClaimsDTO;

import io.netty.handler.codec.base64.Base64;
import reactor.core.publisher.Mono;

@Component
public class RequiredTokenGatewayFilterFactory extends AbstractGatewayFilterFactory<RequiredTokenGatewayFilterFactory.Config> {

    private final WebClient.Builder webClientBuilder;

    @Value("${request.key}")
    private String requestKey;

    @Value("${request.secret")
    private String requestSecret;

    public RequiredTokenGatewayFilterFactory(final WebClient.Builder webClientBuilder) {
        super(Config.class);
        this.webClientBuilder = webClientBuilder;
    }

    @SuppressWarnings("unchecked")
    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            List<String> header = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION);

            if (header == null) {
                exchange.getResponse().setStatusCode(HttpStatusCode.valueOf(HttpStatus.BAD_REQUEST.value()));
                return exchange.getResponse().setComplete();
            }

            String authorization = header.get(0);
                
            if (!authorization.startsWith("Bearer ")) {
                exchange.getResponse().setStatusCode(HttpStatusCode.valueOf(HttpStatus.BAD_REQUEST.value()));
                return exchange.getResponse().setComplete();
            }

            String token = authorization.split(" ")[1];

            return webClientBuilder
            .build()
            .post()
            .bodyValue(token)
            .retrieve()
            .bodyToMono(Map.class)
            .flatMap((result) -> {
                String sub = (String) result.get("sub");
                Collection<String> scope = (Collection<String>) result.get("scope"); 
                
                String claims = String.format("%s&%s", sub, scope);

                ServerWebExchange myExch = exchange.mutate().request(exchange
                .getRequest()
                .mutate()
                .header("X-Auth", claims)
                .build()).build();
                
                return chain.filter(myExch);
            })
            .onErrorResume((throwable) -> {
                System.out.println(throwable.getMessage());
                return Mono.error(throwable);
            });
        };
    }
    
    public static class Config {

    }
}
