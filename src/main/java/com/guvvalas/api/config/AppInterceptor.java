package com.guvvalas.api.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class AppInterceptor implements WebFilter {
    /**
     * Process the Web request and (optionally) delegate to the next
     * {@code WebFilter} through the given {@link WebFilterChain}.
     *
     * @param exchange the current server exchange
     * @param chain    provides a way to delegate to the next filter
     * @return {@code Mono<Void>} to indicate when request processing is complete
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {

        ServerHttpRequest request = exchange.getRequest();
        exchange = exchange.mutate().request(request).build();

        var response = exchange.getResponse().getHeaders();
        response.add("Access-Control-Allow-Headers", "*");
        response.add("Access-Control-Allow-Origin", "*");
        response.add("Access-Control-Allow-Methods", "*");
        response.add("Access-Control-Max-Age", "3600");
        response.add("Access-Control-Expose-Headers", "*");
        response.add("Access-Control-Allow-Headers", "X-requested-with, Content-Type,Accept, Origin");
        response.add("Cache-Control", "private, no-store, no-cache, must-revalidate");
        response.add("Pragma", "no-cache");


        return chain.filter(exchange);
    }


    @Bean
    CorsWebFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        config.addExposedHeader("*");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return new CorsWebFilter(source);
    }

}
