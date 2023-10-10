package com.nadeeshani.clickForTrips_app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/api/?**", "/booking/?**").permitAll() // Allow unrestricted access to URLs starting with "/api/"
                                .requestMatchers("/customer").hasRole("ADMIN") // Match requests to "/customer/" and "/booking/" and their sub-paths
                                .anyRequest().authenticated()

                )
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults())
                .userDetailsService(userDetailsService()); // Define your custom userDetailsService here

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        // Define your custom UserDetailsService here for in-memory authentication
        // You can use InMemoryUserDetailsManager or your custom implementation
        return new InMemoryUserDetailsManager(
                User.withDefaultPasswordEncoder()
                        .username("user")
                        .password("pass")
                        .roles("USER")
                        .build(),
                User.withDefaultPasswordEncoder()
                        .username("admin")
                        .password("admin")
                        .roles("ADMIN")
                        .build()
        );
    }


}

