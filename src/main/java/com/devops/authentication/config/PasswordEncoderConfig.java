package com.devops.authentication.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Password encoder configuration.
 *
 * BCrypt is the recommended password hashing algorithm
 * for Spring Security applications.
 */
@Configuration
public class PasswordEncoderConfig {

    /**
     * Password encoder bean.
     *
     * This bean is injected anywhere password hashing
     * or password verification is required.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}