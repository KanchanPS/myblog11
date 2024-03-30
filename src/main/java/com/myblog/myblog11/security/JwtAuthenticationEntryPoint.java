package com.myblog.myblog11.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    // this commence method does while your giving a jwtToken which doesn,t have the valid userDetails then its an unAutherised request
   // example- if the token is valid but the role of the token is USER, the user role can't create a post , the token is valid but the token has the role ADMIN then this particular exception is not be thrown
   // token provided does it have an access to the resource or not so the token can be USER or Admin so the ADMIN create post but USER Can't
    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED,
                authException.getMessage());
    }
}

