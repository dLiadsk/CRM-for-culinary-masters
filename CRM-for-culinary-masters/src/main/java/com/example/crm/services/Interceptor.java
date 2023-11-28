package com.example.crm.services;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class Interceptor implements HandlerInterceptor {
    private final UserService userService;

    public Interceptor(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
        String authorizationHeader = request.getHeader("Authorization");

        if(authorizationHeader == null)
            throw new RuntimeException("Authorization header is missing or invalid");

        request.setAttribute("user",userService.getUserFromToken(authorizationHeader.substring(7)));

        return true;
    }
}
