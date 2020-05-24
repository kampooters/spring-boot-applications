package com.org.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


/**
 * @author abdul.rehman4
 * @version 1.0
 * @since v1.0
 * Authentication config. Sets @{@link BCryptPasswordEncoder} for pluggedin oAuth
 */
@Configuration
public class AuthConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
