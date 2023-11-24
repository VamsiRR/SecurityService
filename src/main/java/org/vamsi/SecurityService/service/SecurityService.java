package org.vamsi.SecurityService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vamsi.SecurityService.dto.UserCredentials;
import org.vamsi.SecurityService.repo.SecurityServiceRepository;
import org.vamsi.SecurityService.util.JWTUtil;

@Service
public class SecurityService {

    @Autowired
    private SecurityServiceRepository repo;

    @Autowired
    private JWTUtil jwtUtil;

    public void registerUser(UserCredentials credentials)
    {
        try
        {
            repo.save(credentials);
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
