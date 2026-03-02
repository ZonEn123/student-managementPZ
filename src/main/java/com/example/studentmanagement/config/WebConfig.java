package com.example.studentmanagement.config;

import com.example.studentmanagement.config.interceptor.JwtInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new JwtInterceptor())
                // 拦截所有接口
                .addPathPatterns("/**")
                // 放行无需认证的地址
                .excludePathPatterns(
                        "/login", // 登录接口
                        "/swagger-ui/**", // 接口文档界面
                        "/v3/api-docs/**", // 接口文档数据
                        "/swagger-ui.html", // 你的文档访问地址
                        "/error"
                );
    }
}