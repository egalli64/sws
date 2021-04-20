package com.example.sws.s07;

import java.util.Set;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

import com.example.sws.s06.SimpleSecurityUser;

// !!! I'm intentionally using the deprecated NoOpPasswordEncoder !!!
@SuppressWarnings("deprecation")
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        var svc = new SimpleUserDetailsService(Set.of(new SimpleSecurityUser("tom", "password")));

        auth.userDetailsService(svc).passwordEncoder(NoOpPasswordEncoder.getInstance());
    }
}