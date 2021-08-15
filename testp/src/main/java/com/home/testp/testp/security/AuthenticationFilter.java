package com.home.testp.testp.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.home.testp.testp.vo.RequestLogin;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/* Filter 에서는 유일하게 request 와 response 에 대한 변조가 가능함. */
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        // request 와 response 는 한번 변조 또는 읽고나면 그 값을 더이상 읽일수 없기때문에 캐시에 담아놓는 작업이 필요함.
        ContentCachingRequestWrapper httpServletRequest = new ContentCachingRequestWrapper((ContentCachingRequestWrapper)request);
        ContentCachingResponseWrapper httpServletResponse = new ContentCachingResponseWrapper((ContentCachingResponseWrapper)response);

        try{
            RequestLogin creds = new ObjectMapper().readValue(httpServletRequest.getInputStream()
                                                             ,RequestLogin.class);
            return getAuthenticationManager().authenticate(
                     new UsernamePasswordAuthenticationToken(creds.getEmail()
                                                            ,creds.getPassword()
                                                            ,new ArrayList<>()
                                                            )
                                                          );
        } catch (Exception e) {
                 throw new RuntimeException(e);
        }
    }


    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        super.successfulAuthentication(request, response, chain, authResult);
    }
}
