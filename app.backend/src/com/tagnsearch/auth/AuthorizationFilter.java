package com.tagnsearch.auth;

import com.tagnsearch.utils.PasswordUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by JS on 8/21/16.
 */
// TODO we need to refactor this class
// use constants and less if
public class AuthorizationFilter extends GenericFilterBean  {

    private static final String LOGIN_URL = "/login";
    private static final String REGISTRATION_URL = "/registration";

    @Override
    public void doFilter(final ServletRequest req,
                         final ServletResponse res,
                         final FilterChain chain) throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) req;
        final HttpServletResponse response = (HttpServletResponse) res;

        final String authHeader = request.getHeader("Authorization");
        if ( request.getRequestURI().equals(LOGIN_URL) || request.getRequestURI().equals(REGISTRATION_URL)) {
            chain.doFilter(req, res);
        } else if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            response.sendRedirect(LOGIN_URL);
        } else {
            final String token = authHeader.substring(7); // The part after "Bearer "

            if ( !PasswordUtils.isTokenActicve(token) ) {
                response.sendRedirect(LOGIN_URL);
            }

            try {
                request.setAttribute("claims", PasswordUtils.getClaims(token));
            } catch (final Exception e) {
                throw new ServletException("Invalid token!");
            }
            chain.doFilter(req, res);
        }
    }

}
