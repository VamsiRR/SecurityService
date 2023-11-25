package org.vamsi.securityservice.service;

import io.micrometer.common.util.StringUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class AuthFilter extends OncePerRequestFilter
{
    @Autowired
    private JWTService jwtService;

    @Autowired
    private UserEntityUserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException
    {
        final String BEARER = "Bearer ";

        String token = null;

        String[] claims = null;

        String userName = null;

        String authHeader = request.getHeader("Authorization");

        if(StringUtils.isNotEmpty(authHeader) && authHeader.startsWith(BEARER))
        {
            token = authHeader.substring(BEARER.length());

            userName = jwtService.extractUsername(token);

        }

        if(userName != null && SecurityContextHolder.getContext() == null)
        {
            //Load user details by userName
            UserDetails userDetails = userDetailsService.loadUserByUsername(userName);

            //Validate Token
            if(jwtService.validateToken(token, userDetails))
            {
                //Create auth token
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                //Add auth token to the security context
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }

            filterChain.doFilter(request, response);
        }

    }
}
