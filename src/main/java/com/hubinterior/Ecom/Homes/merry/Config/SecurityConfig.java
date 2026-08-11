package com.hubinterior.Ecom.Homes.merry.Config;

import com.hubinterior.Ecom.Homes.merry.filter.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)

                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                .authorizeHttpRequests(auth -> auth

                        // ── Auth (public) ─────────────────────────────────────────────────
                        .requestMatchers(HttpMethod.POST, "/api/auth/login").permitAll()

                        // ── User (public registration) ────────────────────────────────────
                        .requestMatchers(HttpMethod.POST, "/api/v1/CreateUser").permitAll()

                        // ── Category (admin only) ─────────────────────────────────────────
                        .requestMatchers(HttpMethod.POST, "/api/v1/CreateCategory").hasRole("ADMIN")

                        // ── Products — public reads ───────────────────────────────────────
                        .requestMatchers(HttpMethod.GET, "/api/v1/products/getAllProducts").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/products/getProduct/**").permitAll()

                        // ── Products — admin writes ───────────────────────────────────────
                        .requestMatchers(HttpMethod.POST, "/api/v1/products/createProduct")
                        .hasAnyRole("ADMIN", "ENTERPRISER")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/products/updateProduct/**").hasAnyRole("ADMIN", "ENTERPRISER")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/products/updateAllProducts").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/products/deleteProduct/**").hasRole("ADMIN")

                        // ── Pricing (admin only) ──────────────────────────────────────────
                        .requestMatchers(HttpMethod.POST, "/api/v1/pricing/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/v1/pricing/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/pricing/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/pricing/**").hasRole("ADMIN")

                        // ── Inventory (admin only) ────────────────────────────────────────
                        .requestMatchers(HttpMethod.POST, "/api/v1/inventory/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/v1/inventory/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/inventory/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/inventory/**").hasRole("ADMIN")

                        // ── Media (admin only) ────────────────────────────────────────────
                        .requestMatchers(HttpMethod.POST, "/api/v1/media/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/v1/media/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/media/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/media/**").hasRole("ADMIN")

                        // ── SEO (admin only) ──────────────────────────────────────────────
                        .requestMatchers(HttpMethod.POST, "/api/v1/seo/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/v1/seo/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/seo/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/seo/**").hasRole("ADMIN")

                        // ── Sourcing & Logistics (admin only) ─────────────────────────────
                        .requestMatchers(HttpMethod.POST, "/api/v1/sourcing/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/v1/sourcing/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/sourcing/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/sourcing/**").hasRole("ADMIN")

                        // ── Specifications (admin only) ───────────────────────────────────
                        .requestMatchers(HttpMethod.POST, "/api/v1/specifications/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/v1/specifications/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/specifications/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/specifications/**").hasRole("ADMIN")

                        // ── Internal (admin only) ─────────────────────────────────────────
                        .requestMatchers(HttpMethod.POST, "/api/v1/internal/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/v1/internal/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/internal/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/internal/**").hasRole("ADMIN")

                        // ── Fallback ──────────────────────────────────────────────────────
                        // CUSTOMER and ENTERPRISER roles are loaded from the DB on every
                        // request via CustomUserDetailsService. No endpoints currently require
                        // CUSTOMER/ENTERPRISER-specific restriction. When such endpoints are
                        // added, insert explicit .hasRole("CUSTOMER") / .hasRole("ENTERPRISER")
                        // rules ABOVE this line — do not rely on this fallback for role-gating.
                        .anyRequest().authenticated())

                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
