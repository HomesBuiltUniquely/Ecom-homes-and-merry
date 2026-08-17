package com.hubinterior.Ecom.Homes.merry.Config;

import com.hubinterior.Ecom.Homes.merry.filter.JwtAuthenticationFilter;
import com.hubinterior.Ecom.Homes.merry.Domain.user.enums.UserRole;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
                        .requestMatchers("/api/auth/login").permitAll()

                        // ── User (public registration) ────────────────────────────────────
                        .requestMatchers("/api/v1/CreateUser").permitAll()

                        // ── Category (admin only) ─────────────────────────────────────────
                        .requestMatchers("/api/v1/CreateCategory").hasRole("ADMIN")

                        // ── Products — public reads ───────────────────────────────────────
                        .requestMatchers("/api/v1/products/getAllProducts").permitAll()
                        .requestMatchers("/api/v1/products/getProduct/**").permitAll()

                        // ── Products — bulk update (STRICTLY ADMIN ONLY) ───────────────────
                        .requestMatchers("/api/v1/products/updateAllProducts").hasRole("ADMIN")

                        // ── Products — single product write permissions (ADMIN & ENTERPRISE)
                        .requestMatchers("/api/v1/products/createProduct")
                        .hasAnyRole(UserRole.ADMIN.name(), UserRole.ENTERPRISE.name())
                        .requestMatchers("/api/v1/products/updateProduct/**")
                        .hasAnyRole(UserRole.ADMIN.name(), UserRole.ENTERPRISE.name())
                        .requestMatchers("/api/v1/products/deleteProduct/**")
                        .hasAnyRole(UserRole.ADMIN.name(), UserRole.ENTERPRISE.name())

                        // ── Sub-modules (admin & enterprise) ──────────────────────────────
                        .requestMatchers("/api/v1/pricing/**")
                        .hasAnyRole(UserRole.ADMIN.name(), UserRole.ENTERPRISE.name())

                        .requestMatchers("/api/v1/inventory/**")
                        .hasAnyRole(UserRole.ADMIN.name(), UserRole.ENTERPRISE.name())

                        .requestMatchers("/api/v1/media/**")
                        .hasAnyRole(UserRole.ADMIN.name(), UserRole.ENTERPRISE.name())

                        .requestMatchers("/api/v1/seo/**")
                        .hasAnyRole(UserRole.ADMIN.name(), UserRole.ENTERPRISE.name())

                        .requestMatchers("/api/v1/specifications/**")
                        .hasAnyRole(UserRole.ADMIN.name(), UserRole.ENTERPRISE.name())

                        .requestMatchers("/api/v1/internal/**")
                        .hasAnyRole(UserRole.ADMIN.name(), UserRole.ENTERPRISE.name())

                        // ── Fallback ──────────────────────────────────────────────────────
                        .anyRequest().authenticated())

                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

}
