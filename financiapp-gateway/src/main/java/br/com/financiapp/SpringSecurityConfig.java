package br.com.financiapp;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@EnableWebFluxSecurity
public class SpringSecurityConfig {

    @Bean
    public SecurityWebFilterChain springSecurity(ServerHttpSecurity http) throws Exception {
        http.cors();//Resolve o problema do OPTIONS retornar 401
        http.csrf().disable();//Desabilita o CSRF, afinal estamos usando o token do keycloak
        return http.build();
    }

}
