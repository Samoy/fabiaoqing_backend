package com.samoy.fabiaoqing.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * WebSecurityConfig
 *
 * @author Samoy
 * @date 2019/9/3
 */
@Configuration
public class WebSecurityConfig implements WebMvcConfigurer {
    @Resource
    private WebSecurityInterceptor webSecurityInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(webSecurityInterceptor)
                .addPathPatterns("/user/**")
                .excludePathPatterns("/user/login", "/user/register", "/user/send_code", "/user/login_by_code", "/user/find_by_tel", "/user/reset_psd")
                .addPathPatterns("/favorite/**");
    }
}
