package com.basicmodule.security;

import com.basicmodule.auth.outh.PrincipalDetailsOauth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
// secured 어노테이션 활성화함. // preAuth 활성화
// postAuthorize 도 동시에 활성화 시킴
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final PrincipalDetailsOauth2UserService principalOauth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable();

        http.authorizeRequests()
        // user 로 유입되는 경로는 인증된 유저만 유입이 가능하도록 설정
        .antMatchers("/user/**").authenticated()

        .antMatchers("/manager/**")
        .access("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
        .antMatchers("/admin/**")
        .access("hasRole('ROLE_ADMIN')")
        .anyRequest()
        .permitAll()
        .and()

        // formLogin -> 인증이 안된 경우 login 페이지로 유저 토스
        .formLogin()

        // 로그인 프로세스를 진행할 uri 값 설정 default => /login
        // login 이라는 주소가 호출되었을경우 스프링 security 가 낚아채서 로그인을 진행
        .loginPage("/loginForm")
        .loginProcessingUrl("/login")
        .defaultSuccessUrl("/")
        .and()

        // 구글 로그인이 완료된 뒤의 후처리가 필요함.
        // 1.코드받기(인증)  2. 엑세스토큰(권한) 3.사용자프로필 정보를 갖고옴 4. 그 정보를 토대로 회원가입을 진행함
        .oauth2Login()
        .loginPage("/loginForm")
        .userInfoEndpoint()
        .userService(principalOauth2UserService);

    }
}
