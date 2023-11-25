package org.vamsi.SecurityService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.vamsi.SecurityService.dto.UserEntity;
import org.vamsi.SecurityService.repo.SecurityServiceRepository;

import java.util.Optional;

@Service
public class UserEntityUserDetailsService implements UserDetailsService {

    @Autowired
    private SecurityServiceRepository securityServiceRepository;

    @Override
    public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        Optional<UserEntity> userEntityOptional = securityServiceRepository.findById(username);

        return userEntityOptional.map(UserDetailsServiceMapper::new).orElseThrow(() -> new UsernameNotFoundException("User not found Exception!"));
    }
}
