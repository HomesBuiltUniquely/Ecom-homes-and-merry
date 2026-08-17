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

import java.util.ArrayList;

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

                        .requestMatchers("/api/auth/login").permitAll()

                        .requestMatchers("/api/v1/CreateUser").permitAll()

                        // ── Categories — public reads ─────────────────────────────────────
                        .requestMatchers("/api/v1/categories/getAllCategories").permitAll()
                        .requestMatchers("/api/v1/categories/getCategory/**").permitAll()
                        .requestMatchers("/api/v1/secondary-categories/getAllCategories").permitAll()
                        .requestMatchers("/api/v1/secondary-categories/getCategory/**").permitAll()
                        .requestMatchers("/api/v1/secondary-categories/getCategoriesByPrimary/**").permitAll()

                        // ── Categories — admin writes ─────────────────────────────────────
                        .requestMatchers("/api/v1/categories/createCategory").hasRole("ADMIN")
                        .requestMatchers("/api/v1/categories/updateCategory/**").hasRole("ADMIN")
                        .requestMatchers("/api/v1/categories/deleteCategory/**").hasRole("ADMIN")
                        .requestMatchers("/api/v1/secondary-categories/createCategory/**").hasRole("ADMIN")
                        .requestMatchers("/api/v1/secondary-categories/createSubCategory/**").hasRole("ADMIN")
                        .requestMatchers("/api/v1/secondary-categories/updateCategory/**").hasRole("ADMIN")
                        .requestMatchers("/api/v1/secondary-categories/deleteCategory/**").hasRole("ADMIN")

                        // ── Products — public reads ───────────────────────────────────────
                        .requestMatchers("/api/v1/products/getAllProducts").permitAll()
                        .requestMatchers("/api/v1/products/getProduct/**").permitAll()

                        // ── Products — admin writes ───────────────────────────────────────
                        .requestMatchers("/api/v1/products/createProduct")
                        .hasAnyRole(UserRole.ADMIN.name(), UserRole.ENTERPRISE.name())
                        .requestMatchers("/api/v1/products/updateProduct/**")
                        .hasAnyRole(UserRole.ADMIN.name(), UserRole.ENTERPRISE.name())
                        .requestMatchers("/api/v1/products/updateAllProducts").hasRole("ADMIN")
                        .requestMatchers("/api/v1/products/deleteProduct/**").hasRole("ADMIN")

                        // ── Pricing (admin only) ──────────────────────────────────────────
                        .requestMatchers("/api/v1/pricing/createPricing/{prod_id}")
                        .hasAnyRole(UserRole.ADMIN.name(), UserRole.ENTERPRISE.name())

                        // ── Inventory (admin only) ────────────────────────────────────────
                        .requestMatchers("/api/v1/inventory/**").hasRole("ADMIN")

                        // ── Media (admin only) ────────────────────────────────────────────
                        .requestMatchers("/api/v1/media/**").hasRole("ADMIN")

                        // ── SEO (admin only) ──────────────────────────────────────────────
                        .requestMatchers("/api/v1/seo/**").hasRole("ADMIN")

                        // ── Specifications (admin only) ───────────────────────────────────
                        .requestMatchers("/api/v1/specifications/**").hasRole("ADMIN")

                        // ── Internal (admin only) ─────────────────────────────────────────
                        .requestMatchers("/api/v1/internal/**").hasRole("ADMIN")

                        .anyRequest().authenticated())

                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

}
