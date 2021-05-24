package com.example.sws;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private static final Logger log = LogManager.getLogger(SecurityConfiguration.class);

    @Value("${custom.security.configuration}")
    private String customSecurityConfiguration;

    @Bean
    public UserDetailsService uds() {
        var manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("tom") //
                .password("$2a$10$BsXAGpkEe6YRV2KbJ996ReSkflDfZgPxpaDq/6B7Y15nVBT6yuo3W") //
                .authorities("read").build());
        manager.createUser(User.withUsername("bob") //
                .password("$2a$10$BsXAGpkEe6YRV2KbJ996ReSkflDfZgPxpaDq/6B7Y15nVBT6yuo3W") //
                .authorities("write").build());
        manager.createUser(User.withUsername("kim") //
                .password("$2a$10$BsXAGpkEe6YRV2KbJ996ReSkflDfZgPxpaDq/6B7Y15nVBT6yuo3W") //
                .authorities("update").build());
        manager.createUser(User.withUsername("amy") //
                .password("$2a$10$BsXAGpkEe6YRV2KbJ996ReSkflDfZgPxpaDq/6B7Y15nVBT6yuo3W") //
                .authorities("read", "update").build());
        return manager;
    }

    @Bean
    public PasswordEncoder pe() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        log.traceEntry("custom.security.configuration set to {}", customSecurityConfiguration);
        var any = http.authorizeRequests().anyRequest();

        switch (customSecurityConfiguration) {
        case "readWrite":
            any.hasAnyAuthority("read", "write");
            break;
        case "readNotUpdate":
            // Spring Expression Language for complex rules
            any.access("hasAuthority('read') and !hasAuthority('update')");
            break;
        default:
            any.hasAuthority("read");
            break;
        }

        http.formLogin().and().httpBasic();
    }
}