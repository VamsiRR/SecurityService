package org.vamsi.securityservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.vamsi.securityservice.dto.UserEntity;
import org.vamsi.securityservice.repo.SecurityServiceRepository;

import java.util.Optional;

@Service
public class UserEntityUserDetailsService implements UserDetailsService {

    @Autowired
    private SecurityServiceRepository securityServiceRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        Optional<UserEntity> userEntityOptional = securityServiceRepository.findById(username);

        return userEntityOptional.map(UserDetailsMapper::new).orElseThrow(() -> new UsernameNotFoundException("User not found Exception!"));
    }
}
