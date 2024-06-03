package org.example.proiect.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Aceasta clasa stabileste daca anumite requesturi vor fi executate in functie de starea utilizatorului, logat/nelogat
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
                // Disable Cross-Site Request Forgery (CSRF) protection
                .csrf(csrf -> csrf.disable())

                // Authorize HTTP requests
                .authorizeHttpRequests(authorize -> authorize.
                        //.requestMatchers("/v2/api-docs", "/configuration/ui", "/swagger-resources/**", "/configuration/security", "/swagger-ui/**", "/webjars/**").permitAll()

                        // Allow all methods under "/api/v1/auth/" to be accessed without authentication
                        //.requestMatchers("/api/v1/auth/**").permitAll()
                        // Require authentication for any other request
                        //.anyRequest().authenticated())
                        //.anyRequest().permitAll())
                        requestMatchers("/v3/api-docs/**",
                                "/swagger-ui/**",
                                "/swagger-ui.html",
                                "auth/**").permitAll()
                                .anyRequest().authenticated())
                // Configure session management
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // Set the authentication provider
                .authenticationProvider(authenticationProvider)

                // Add a custom filter for JWT authentication before the UsernamePasswordAuthenticationFilter
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);



        return http.build();
    }
}
