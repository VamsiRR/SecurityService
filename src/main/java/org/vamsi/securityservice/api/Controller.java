package org.vamsi.securityservice.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.vamsi.securityservice.dto.AuthRequest;
import org.vamsi.securityservice.dto.UserEntity;
import org.vamsi.securityservice.dto.UserRequest;
import org.vamsi.securityservice.service.JWTService;
import org.vamsi.securityservice.service.SecurityService;

@RestController
@RequestMapping("/security")
public class Controller {

    private final Logger logger = LoggerFactory.getLogger(Controller.class);

    @Autowired
    private SecurityService securityService;

    @Autowired
    private JWTService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public void registerUser(@RequestBody UserRequest request)
    {
        logger.info("Request received to register email: " + request.email());
        securityService.registerUser(new UserEntity(request.firstName(), request.lastName(), request.email(), request.password(), "user"));
    }

    @PostMapping("/login")
    public String login(AuthRequest authRequest)
    {
        logger.info("Request received to generate token for username: " + authRequest.email());
        return securityService.generateToken(authRequest);
    }

    @GetMapping("/refresh")
    public String refreshToken(String currentToken)
    {
        return null;
    }

    @PostMapping("/validate")
    public boolean validateToken(String token)
    {
        return securityService.validate(token);
    }
}
