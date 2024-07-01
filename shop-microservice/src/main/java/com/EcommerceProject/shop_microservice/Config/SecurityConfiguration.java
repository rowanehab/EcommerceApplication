package com.EcommerceProject.shop_microservice.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authz -> {
                   //authz.requestMatchers("/api/users/save").permitAll();
                    //authz.requestMatchers("/api/users/login").permitAll();
                    //authz.requestMatchers("/SadRo");
                    //authz.requestMatchers("/okRo");
                    authz.anyRequest().authenticated();
                });
        http.httpBasic(Customizer.withDefaults());
        return http.build();
    }
}