package it.unicam.coloni.hackhub.context.identity.infrastructure.security.filters;

import it.unicam.coloni.hackhub.context.identity.application.utility.JWTHelper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private AuthenticationProvider manager;
    @Autowired
    private JWTHelper jwtHelper;



    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if(this.supports(request)) {
            String token = this.getToken(request);
            String username = jwtHelper.extractUsername(token);

            Authentication authentication = UsernamePasswordAuthenticationToken.unauthenticated(username,null);

            Authentication authenticated = manager.authenticate(authentication);
            SecurityContextHolder.getContext().setAuthentication(authenticated);
        }
        filterChain.doFilter(request, response);
    }


    private boolean supports(HttpServletRequest request){
        String token = request.getHeader("Authorization");

        return token!=null && token.startsWith("Bearer");
    }

    private String getToken (HttpServletRequest request){
        return request.getHeader("Authorization").substring(7);
    }


}
