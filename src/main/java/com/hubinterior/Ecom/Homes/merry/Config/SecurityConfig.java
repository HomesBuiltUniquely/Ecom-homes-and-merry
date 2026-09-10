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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

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
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .csrf(AbstractHttpConfigurer::disable)

                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                .authorizeHttpRequests(auth -> auth
                        // ── Auth (public) ─────────────────────────────────────────────────
                        .requestMatchers("/api/auth/login").permitAll()

                        // ── User (public registration) ────────────────────────────────────
                        .requestMatchers("/api/v1/CreateUser").permitAll()

                        // ── Designer Lead Queue Endpoints ─────────────────────────────────
                        .requestMatchers("/api/leads/queue", "/api/v1/leads/queue")
                        .hasAnyRole(UserRole.DESIGNERS.name(), UserRole.DESIGN_MANAGERS.name(), UserRole.TDM.name(), UserRole.ADMIN.name())

                        // ── CRM Sales Lead Filter Endpoints ───────────────────────────────
                        .requestMatchers("/v1/leads/filter", "/api/v1/leads/filter")
                        .hasAnyRole(UserRole.SALES_EXECUTIVE.name(), UserRole.SALES_MANAGER.name(), UserRole.SALES_ADMIN.name(), UserRole.ADMIN.name())

                        // ── Prolance Quote Link Endpoints ─────────────────────────────────
                        .requestMatchers("/Origin/Quotes/FullDetails/**")
                        .hasAnyRole(
                                UserRole.DESIGNERS.name(),
                                UserRole.DESIGN_MANAGERS.name(),
                                UserRole.TDM.name(),
                                UserRole.SALES_EXECUTIVE.name(),
                                UserRole.SALES_MANAGER.name(),
                                UserRole.SALES_ADMIN.name(),
                                UserRole.ADMIN.name()
                        )

                        // ── Primary Categories ───────────────────────────────────────────
                        .requestMatchers("/api/v1/categories/getAllCategories").permitAll()
                        .requestMatchers("/api/v1/categories/getCategory/**").permitAll()
                        .requestMatchers("/api/v1/categories/createCategory").hasAnyRole(UserRole.ADMIN.name(), UserRole.ENTERPRISE.name())
                        .requestMatchers("/api/v1/categories/updateCategory/**").hasAnyRole(UserRole.ADMIN.name(), UserRole.ENTERPRISE.name())
                        .requestMatchers("/api/v1/categories/deleteCategory/**").hasRole(UserRole.ADMIN.name())

                        // ── Secondary Categories ─────────────────────────────────────────
                        .requestMatchers("/api/v1/secondary-categories/getAllCategories").permitAll()
                        .requestMatchers("/api/v1/secondary-categories/getCategory/**").permitAll()
                        .requestMatchers("/api/v1/secondary-categories/getCategoriesByPrimary/**").permitAll()
                        .requestMatchers("/api/v1/secondary-categories/createCategory/**").hasAnyRole(UserRole.ADMIN.name(), UserRole.ENTERPRISE.name())
                        .requestMatchers("/api/v1/secondary-categories/createSubCategory/**").hasAnyRole(UserRole.ADMIN.name(), UserRole.ENTERPRISE.name())
                        .requestMatchers("/api/v1/secondary-categories/updateCategory/**").hasAnyRole(UserRole.ADMIN.name(), UserRole.ENTERPRISE.name())
                        .requestMatchers("/api/v1/secondary-categories/deleteCategory/**").hasRole(UserRole.ADMIN.name())

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

                        // ── Internal Microservice Endpoints ───────────────────────────────
                        .requestMatchers("/api/v1/products/internal/**").permitAll()
                        .requestMatchers("/api/v1/inventory/internal/**").permitAll()

                        .requestMatchers(
                                "/swagger-ui/**",
                                "/swagger-ui.html",
                                "/v3/api-docs/**"
                        ).permitAll()

                        // ── Fallback ──────────────────────────────────────────────────────
                        .anyRequest().authenticated())

                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        // Allow localhost on any port (Next.js, Vite, React dev servers) and production domains
        configuration.setAllowedOriginPatterns(List.of(
                "http://localhost:[*]",
                "http://127.0.0.1:[*]",
                "https://*.homesandmerry.com"
        ));

        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS", "HEAD"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setExposedHeaders(Arrays.asList("Authorization", "X-Correlation-Id", "Content-Disposition"));
        configuration.setAllowCredentials(true);
        configuration.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}
