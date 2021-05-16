package com.example.sws.s07;

import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class SimpleUserDetailsService implements UserDetailsService {
    private static final Logger log = LogManager.getFormatterLogger(SimpleUserDetailsService.class);
    private Set<UserDetails> users;

    public SimpleUserDetailsService(Set<UserDetails> users) {
        this.users = users;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.traceEntry("loadUserByUsername {}", username);
        return users.stream().filter(u -> u.getUsername().equals(username)).findAny().orElseThrow( //
                () -> new UsernameNotFoundException("User '" + username + "' not found"));
    }
}