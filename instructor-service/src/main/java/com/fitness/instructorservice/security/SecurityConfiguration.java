package com.fitness.instructorservice.security;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@SpringBootConfiguration
@EnableReactiveMethodSecurity
public class SecurityConfiguration {
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
                .formLogin().disable()
                .authorizeExchange()
                .pathMatchers("/programs", "/programs/**", "/categories", "localhost:4200/**", "/categories/**","/**","/quotes-reactive-paged*").permitAll()
                .and()
                .build();
    }
}
