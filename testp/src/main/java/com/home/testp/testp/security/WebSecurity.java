package com.home.testp.testp.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFilter;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override // 권한관련 처리
    protected void configure(HttpSecurity http) throws Exception {
        // 현재 csrf 인증키가 없기때문에 csrf 암호화 처리를 임시 비활성화
        http.csrf().disable();

        http.authorizeRequests()
                .antMatchers("/login").permitAll()
                .anyRequest().authenticated();


    }

  /*  private AuthenticationFilter getAuthenticationFilter() throws Exception{

        AuthenticationFilter authenticationFilter = new AuthenticationFilter();
        authenticationFilter.setAuthenticationManager(authenticationManager());

        return authenticationFilter;

    }*/

    /*@Override // 인증처리
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder);
    }*/

}
