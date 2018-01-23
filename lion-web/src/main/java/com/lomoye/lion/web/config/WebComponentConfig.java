package com.lomoye.lion.web.config;

import com.lomoye.lion.web.filter.LionSessionFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

/**
 * Created by lomoye on 2017/10/19.
 * web组件配置 过滤器等
 */
@Configuration
public class WebComponentConfig {

    @Bean
    public FilterRegistrationBean filterDemo3Registration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(lionSessionFilter());
        registration.addUrlPatterns("/api/*");
        registration.setName("lionSessionFilter");
        registration.setOrder(0);
        return registration;
    }

    @Bean
    public Filter lionSessionFilter() {
        return new LionSessionFilter();
    }
}
