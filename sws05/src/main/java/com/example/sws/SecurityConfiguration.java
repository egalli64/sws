package com.example.sws;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
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
    private static final Logger log = LogManager.getLogger(SecurityConfiguration.class);
    @Value("${custom.security.configuration}")
    private String customSecurityConfiguration;

    /**
     * Variations on WebSecurityConfigurerAdapter standard implementation
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        log.traceEntry("custom.security.configuration " + customSecurityConfiguration);

        // ExpressionUrlAuthorizationConfigurer<HttpSecurity>.AuthorizedUrl
        var authorizedAny = http.authorizeRequests().anyRequest();

        switch (customSecurityConfiguration) {
        case "useless":
            // !!! basic HTTP authentication but any request by anyone is authorized !!!
            authorizedAny.permitAll().and().httpBasic();
            break;
        case "noForm":
            // any request should be authenticated - but no login/logout pages!
            authorizedAny.authenticated().and().httpBasic();
            break;
        default:
            // default Spring Security configuration behavior
            authorizedAny.authenticated().and().formLogin().and().httpBasic();
            break;
        }
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        var manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("tom").password("password").authorities("plain").build());

        auth.userDetailsService(manager).passwordEncoder(NoOpPasswordEncoder.getInstance());
    }
}