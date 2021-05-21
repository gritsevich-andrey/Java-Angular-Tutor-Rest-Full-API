package com.fitness.lessonservice.web.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class SecurityConfig {
    @Bean
    public SecurityWebFilterChain securityWebFilterChain(
            ServerHttpSecurity http) {
        return http
                .headers(headers -> headers
                        .cache(ServerHttpSecurity.HeaderSpec.CacheSpec::disable
                        )
                )
                .csrf().disable()
                .httpBasic().disable()
                .authorizeExchange()
                .pathMatchers("/lessons**", "/lessons/**", "/lessons/history/**",
                        "/actuator", "/actuator/**","/**").permitAll()
                .and()
                .build();
    }

}
