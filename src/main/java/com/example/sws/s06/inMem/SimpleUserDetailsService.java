package com.example.sws.s06.inMem;

import java.util.Set;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class SimpleUserDetailsService implements UserDetailsService {
    private Set<UserDetails> users;

    public SimpleUserDetailsService(Set<UserDetails> users) {
        this.users = users;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return users.stream().filter(u -> u.getUsername().equals(username)).findAny().orElseThrow( //
                () -> new UsernameNotFoundException("User '" + username + "' not found"));
    }
}