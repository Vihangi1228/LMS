/*package com.gl.lms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity // Enables @PreAuthorize annotations
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
            // Disable CSRF for simplicity (use carefully in production)
            .csrf().disable()

            // Authorization rules
            .authorizeHttpRequests(auth -> auth
                .anyRequest().authenticated() // All requests require authentication
            )

            // Basic HTTP authentication
            .httpBasic();

        return http.build();
    }
}
*/

package com.gl.lms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .csrf().disable()

                .authorizeHttpRequests(auth -> auth
                        // Only ADMIN can delete books
                        .requestMatchers("/books/**").hasAnyRole("ADMIN", "LIBRARIAN")

                        // Only ADMIN can manage users
                        .requestMatchers("/users/**").hasRole("ADMIN")

                        // Everything else requires login
                        .anyRequest().authenticated()
                )

                .httpBasic();

        return http.build();
    }
}
