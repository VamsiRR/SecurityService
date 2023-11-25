package org.vamsi.SecurityService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vamsi.SecurityService.dto.UserEntity;
import org.vamsi.SecurityService.repo.SecurityServiceRepository;

@Service
public class SecurityService {

    @Autowired
    private SecurityServiceRepository repo;

    @Autowired
    private JWTService jwtUtil;

    public void registerUser(UserEntity user)
    {
        try
        {
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
