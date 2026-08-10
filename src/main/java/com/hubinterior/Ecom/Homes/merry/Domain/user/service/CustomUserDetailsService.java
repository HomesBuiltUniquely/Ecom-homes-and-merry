package com.hubinterior.Ecom.Homes.merry.Domain.user.service;

import com.hubinterior.Ecom.Homes.merry.Domain.user.model.UserData;
import com.hubinterior.Ecom.Homes.merry.Domain.user.repository.UserRepo;
import org.jspecify.annotations.NonNull;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepo userStore;

    public CustomUserDetailsService(UserRepo userStore) {
        this.userStore = userStore;
    }

    @Override
    public UserDetails loadUserByUsername(@NonNull String username) throws UsernameNotFoundException {
        UserData user = userStore.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("No user with email: " + username));

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getEmail())
                .password(user.getPassword())
                .roles(user.getRole())
                .build();
    }
}