package org.vamsi.securityservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vamsi.securityservice.config.SecurityServiceConfig;
import org.vamsi.securityservice.dto.UserEntity;
import org.vamsi.securityservice.repo.SecurityServiceRepository;

@Service
public class SecurityService {

    @Autowired
    private SecurityServiceRepository repo;

    @Autowired
    private SecurityServiceConfig config;

    @Autowired
    private JWTService jwtUtil;

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

    public boolean validate(String token)
    {
        return jwtUtil.validate(token);
    }


}
