package org.example.onlinestore.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/public", "/swagger-ui/**", "/auth/login", "/product/**", "/cart/**","/product/**/**").permitAll()
                        .requestMatchers( "/order/**").authenticated()
                )
                .formLogin(form -> form.disable()) // отключаем стандартную форму логина
                .oauth2Login(Customizer.withDefaults()); // используем OAuth2 (например, через Keycloak)
//

        return http.build();
    }
}




