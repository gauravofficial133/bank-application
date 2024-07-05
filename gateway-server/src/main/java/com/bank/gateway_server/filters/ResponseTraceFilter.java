package com.bank.gateway_server.filters;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import reactor.core.publisher.Mono;

@Slf4j
@Configuration
public class ResponseTraceFilter {
    @Autowired
    FilterUtility filterUtility;

    @Bean
    public GlobalFilter postGlobalFilter(){
        return ((exchange, chain) -> {
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                HttpHeaders requestHeaders = exchange.getRequest().getHeaders();
                String correlationId = filterUtility.getCorrelationId(requestHeaders);
                if(!exchange.getResponse().getHeaders().containsKey(FilterUtility.CORRELATION_ID)) {
                    log.info("Updated the correlation id to the outbound headers: {}",correlationId);
                    exchange.getResponse().getHeaders().add(FilterUtility.CORRELATION_ID, correlationId);
                }
            }));
        });
    }
}
