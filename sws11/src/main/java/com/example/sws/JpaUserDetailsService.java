package com.example.sws;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

import com.example.sws.dao.UserRepository;

@Service
public class JpaUserDetailsService implements UserDetailsManager {
    private static final Logger log = LogManager.getLogger(JpaUserDetailsService.class);
    private UserRepository repo;

    public JpaUserDetailsService(UserRepository repo) {
        this.repo = repo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.traceEntry("loadUserByUsername for " + username);
        return new SecurityUser(repo.findByUsername(username) //
                .orElseThrow(() -> new UsernameNotFoundException("User '" + username + "' not found")));
    }

    @Override
    public void createUser(UserDetails user) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void updateUser(UserDetails user) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void deleteUser(String username) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public boolean userExists(String username) {
        return repo.findByUsername(username).isPresent();
    }
}