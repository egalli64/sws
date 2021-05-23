package com.example.sws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private ReplacedAuthenticationFilter myAuthFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterAt(myAuthFilter, BasicAuthenticationFilter.class) //
                .addFilterBefore(new PostmanValidationFilter(), BasicAuthenticationFilter.class) //
                .addFilterAfter(new PostAuthenticationFilter(), BasicAuthenticationFilter.class) //
                .authorizeRequests().anyRequest().permitAll();
    }
}