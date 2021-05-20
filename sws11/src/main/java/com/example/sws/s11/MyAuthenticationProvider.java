package com.example.sws.s11;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.sws.JpaUserDetailsService;

@Component
public class MyAuthenticationProvider implements AuthenticationProvider {
    private JpaUserDetailsService uds;
    private PasswordEncoder pe;

    public MyAuthenticationProvider(JpaUserDetailsService uds) {
        this.uds = uds;
        this.pe = new BCryptPasswordEncoder();
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        UserDetails ud = uds.loadUserByUsername(username);
        if (pe.matches(password, ud.getPassword())) {
            return new UsernamePasswordAuthenticationToken(username, password, ud.getAuthorities());
        } else {
            throw new BadCredentialsException("Failure");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
