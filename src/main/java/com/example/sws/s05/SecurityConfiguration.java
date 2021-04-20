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
     * Variations on WebSecurityConfigurerAdapter standard implementation
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // default configuration
//        http.authorizeRequests().anyRequest().authenticated().and().formLogin().and().httpBasic();

        /*
         * when no formLogin() is specified no Spring login form
         * 
         * the browser default is used instead _and_ no login / logout pages available
         */

        // !!! Useless - basic HTTP authentication is used but any request by anyone is authorized !!!
//        http.authorizeRequests().anyRequest().permitAll().and().httpBasic();

        // default behavior, any request should be authenticated (but no login/logout pages!)
        http.authorizeRequests().anyRequest().authenticated().and().httpBasic();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        var manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("tom").password("password").authorities("plain").build());

        auth.userDetailsService(manager).passwordEncoder(NoOpPasswordEncoder.getInstance());
    }
}