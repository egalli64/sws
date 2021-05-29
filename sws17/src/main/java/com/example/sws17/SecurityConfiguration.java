package com.example.sws17;

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
    @Bean
    public UserDetailsService uds() {
        var manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("tom") //
                .password("$2a$10$BsXAGpkEe6YRV2KbJ996ReSkflDfZgPxpaDq/6B7Y15nVBT6yuo3W") //
                .authorities("ROLE_USER").build());
        return manager;
    }

    @Bean
    public PasswordEncoder pe() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests() //
                .mvcMatchers("/").permitAll() //
                .anyRequest().authenticated().and().formLogin().and().httpBasic();
    }
}