package com.microservice.securityapp.security.filters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservice.securityapp.models.UserEntity;
import com.microservice.securityapp.security.jwt.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authorization.AuthorizationResult;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
private JwtUtils jwtUtils;
public JwtAuthenticationFilter(JwtUtils jwtUtils) {
    this.jwtUtils = jwtUtils;
}

@Override
public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException{
    UserEntity userEntity=null;
    String username="";
    String password="";
    try {
        userEntity= new ObjectMapper().readValue(request.getInputStream(), UserEntity.class);
        username=userEntity.getUsername();
        password=userEntity.getPassword();
    }catch (Exception ignored){

    }
    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
    return getAuthenticationManager().authenticate(authenticationToken);

}

@Override
protected  void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
    User user =(User)authResult.getPrincipal();
String token = jwtUtils.generateAccessToken(user.getUsername());
    response.addHeader("Authorization",  token);
    Map<String,Object> httpResponse = new HashMap<>();
    httpResponse.put("token",token);
    httpResponse.put("Message","Authentication Successful");
    httpResponse.put("Username",user.getUsername());
    response.getWriter().write(new ObjectMapper().writeValueAsString(httpResponse));
    response.setStatus(HttpServletResponse.SC_OK);
    response.setContentType("application/json");
    response.getWriter().flush();
    super.successfulAuthentication(request,response,chain,authResult);

}

}


