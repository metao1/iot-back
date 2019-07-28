package com.gro.security.service;

import com.gro.repository.UserRepository;
import com.gro.security.JwtUserFactory;
import com.gro.security.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by Metao.
 */
@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findOneByEmail(username).orElseThrow(() ->
            new UsernameNotFoundException(String.format("No user found with username '%s'.", username))
        );
        return JwtUserFactory.create(user);
    }
}
