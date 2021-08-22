package com.home.jwt.config;

import org.springframework.web.filter.CorsFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        // 서버 응답시 json을 자바스크립트에서 처리할 수 있게 할지를 설정
        // ** 이 설정이 false 일 경우 자바스크립트 json 요청시 응답이 들어오지 않게됨.
        // default false
        config.setAllowCredentials(true);
        // 모든 ip 에 응답을 허용
        config.addAllowedOrigin("*");
        // 모든 header 의 응답을 혀용
        config.addAllowedHeader("*");
        // 모든 메소드에 대한 응답을 허용
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/api/**",config); // api 로 들어오는 모든 설정은 이쪽 config를 타라

        return new CorsFilter(source);
    }
}
