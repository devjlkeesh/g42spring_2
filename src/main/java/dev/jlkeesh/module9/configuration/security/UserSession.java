package dev.jlkeesh.module9.configuration.security;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class UserSession {

    public UserDetails requireUserDetails() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            throw new BadCredentialsException("unauthorized");
        }
        Object principal = authentication.getPrincipal();
        if (principal instanceof UserDetails ud) {
            return ud;
        }
        throw new BadCredentialsException("unauthorized");
    }

}
