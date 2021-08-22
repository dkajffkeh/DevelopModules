package com.home.jwt.config;

import com.home.jwt.filter.FrontFilter;
import com.home.jwt.filter.SecondFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 굳이 Security FilterChain 을 이용하여 Filter를 걸 필요가 없이 필터 등록객체를 이용해도됨.
// ** 시큐리티 필터가 실행된 이후에 실행됨.
@Configuration
public class FilterConfig {

    // 커스텀 필터에 대한 등록처리를 하는 부분.
    @Bean
    public FilterRegistrationBean<FrontFilter> frontFilter(){

        FilterRegistrationBean<FrontFilter> bean = new FilterRegistrationBean<>(new FrontFilter());
        bean.addUrlPatterns("/*");
        bean.setOrder(0); // 번호가 낮을수록 가장 우선순위로 필터가 실행됨.

        return bean;

    }

    // 커스텀 필터에 대한 등록처리를 하는 부분.
    @Bean
    public FilterRegistrationBean<SecondFilter> secondFilter(){

        FilterRegistrationBean<SecondFilter> bean = new FilterRegistrationBean<>(new SecondFilter());
        bean.addUrlPatterns("/*");
        bean.setOrder(1); // 번호가 낮을수록 가장 우선순위로 필터가 실행됨.

        return bean;

    }
}
