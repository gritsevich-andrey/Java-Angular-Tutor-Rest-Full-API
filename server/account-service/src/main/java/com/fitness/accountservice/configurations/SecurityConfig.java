package com.fitness.accountservice.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
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
                .pathMatchers("/users", "/lessons", "/lessons/**",
                        "/users/**", "/login", "/register", "/actuator"
                        , "/actuator/**", "localhost:8081/**", "/instructors", "/**", "/ws/**").permitAll()
                .and()
                .build();


    }
    @Bean
    public ReactiveUserDetailsService userDetailsService() {
        UserDetails user =
                User.withUsername("user")
                        .password("password")
                        .roles("USER", "ADMIN")
                        .build(); //
        return new MapReactiveUserDetailsService(user);
    }
}
