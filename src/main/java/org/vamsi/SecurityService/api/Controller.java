package org.vamsi.SecurityService.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.vamsi.SecurityService.dto.AuthRequest;
import org.vamsi.SecurityService.dto.UserCredentials;
import org.vamsi.SecurityService.service.SecurityService;
import org.vamsi.SecurityService.util.JWTUtil;

@RestController
public class Controller {

    @Autowired
    private SecurityService securityService;

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public void registerUser(@RequestBody UserCredentials credentials)
    {
        System.out.println(credentials);

        securityService.registerUser(credentials);
    }

    @PostMapping("/validate")
    public boolean validateToken(String token)
    {
        return securityService.validate(token);
    }

    /**
     *
     *By pass this endpoint from spring security
     */
    @PostMapping("/authenticate")
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest)
    {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.userName(), authRequest.password()));

        if (authentication.isAuthenticated())
        {
            return jwtUtil.generateToken(authRequest.userName());
        }
        else
        {
            throw new UsernameNotFoundException("Invalid user!");
        }
    }
}
