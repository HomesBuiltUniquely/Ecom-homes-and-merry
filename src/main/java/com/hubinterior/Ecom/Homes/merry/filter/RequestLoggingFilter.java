package com.hubinterior.Ecom.Homes.merry.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jspecify.annotations.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

    @Component
    public class RequestLoggingFilter extends OncePerRequestFilter {

        private static final Logger log = LoggerFactory.getLogger(RequestLoggingFilter.class);

        @Override
        protected void doFilterInternal(@NonNull HttpServletRequest request,
                                        @NonNull HttpServletResponse response,
                                        FilterChain filterChain) throws ServletException, IOException {

            long start = System.currentTimeMillis();

            filterChain.doFilter(request, response);

            long duration = System.currentTimeMillis() - start;
            log.info("<< Completed {} {} -> status {} in {}ms",
                    request.getMethod(), request.getRequestURI(), response.getStatus(), duration);
        }
    }

