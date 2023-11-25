package org.vamsi.SecurityService.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.vamsi.SecurityService.service.UserEntityUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityServiceConfig {

    @Bean
    public UserDetailsService userDetailsService()
    {
        return new UserEntityUserDetailsService();

    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception
    {
        return config.getAuthenticationManager();
    }
}
