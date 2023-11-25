package org.vamsi.securityservice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.vamsi.securityservice.api.Controller;
import org.vamsi.securityservice.config.SecurityServiceConfig;
import org.vamsi.securityservice.dto.AuthRequest;
import org.vamsi.securityservice.dto.UserEntity;
import org.vamsi.securityservice.repo.SecurityServiceRepository;

@Service
public class SecurityService
{
    private final Logger logger = LoggerFactory.getLogger(SecurityService.class);

    @Autowired
    private SecurityServiceRepository repo;

    @Autowired
    private SecurityServiceConfig config;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTService jwtService;

    @Autowired
    private AuthFilter authFilter;

    public void registerUser(UserEntity user)
    {
        try
        {
            user.setPassword(config.passwordEncoder().encode(user.getPassword()));
            repo.save(user);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String generateToken(AuthRequest authRequest)
    {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.email(), authRequest.password()));

        if (authentication.isAuthenticated())
        {
            logger.info("user authenticated. Generating token for username: " + authRequest.email());
            return jwtService.generateToken(authRequest.email());
        }
        else
        {
            throw new UsernameNotFoundException("Invalid user!");
        }
    }

    public boolean validate(String token)
    {
        return true;
    }
}
