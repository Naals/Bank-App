package com.project.banksystemapp.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        return http
                .sessionManagement(
                        session ->
                            session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                ).authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/**")
                        .authenticated()
                        .requestMatchers("/api/super-admin/**")
                        .hasRole("ADMIN")
                        .anyRequest().permitAll())
                .addFilterBefore(new JwtValidator(), BasicAuthenticationFilter.class)
                .csrf(AbstractHttpConfigurer::disable)
                .cors(cors -> cors.configurationSource(corsConfigurationSourse()))
                .build();
    }

    private CorsConfigurationSource corsConfigurationSourse() {
        return request -> {
            CorsConfiguration config = new CorsConfiguration();
            config.setAllowedOrigins(
                    Arrays.asList(
                            "http://localhost:5173/",
                            "http://localhost:3000/"
                    )
            );
            config.setAllowedMethods(Collections.singletonList("*"));
            config.setAllowCredentials(true);
            config.setAllowedHeaders(Collections.singletonList("*"));
            config.setExposedHeaders(Collections.singletonList("Authorization"));
            config.setMaxAge(3600L);
            return config;
        };
    }
}
