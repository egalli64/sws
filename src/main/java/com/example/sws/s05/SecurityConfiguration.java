package com.example.sws.s05;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

// !!! I'm intentionally using the deprecated NoOpPasswordEncoder !!!
@SuppressWarnings("deprecation")
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    /**
     * Close to WebSecurityConfigurerAdapter standard implementation
     * 
     * _but_ no Spring login form, the browser default is used instead
     * 
     * use the "permitAll" variation to disable authorization check
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic();

        // !!! Useless - basic HTTP authentication is used but any request by anyone is authorized !!!
        // http.authorizeRequests().anyRequest().permitAll();

        // default behavior, any request should be authenticated
        http.authorizeRequests().anyRequest().authenticated();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        var manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("tom").password("UnsafePassword").authorities("plain").build());

        auth.userDetailsService(manager).passwordEncoder(NoOpPasswordEncoder.getInstance());
    }
}