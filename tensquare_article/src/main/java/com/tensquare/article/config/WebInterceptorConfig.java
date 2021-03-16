package com.tensquare.article.config;

import com.tensquare.article.interceptor.JwtInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @description:
 * @projectName:tensquare_parent
 * @see:com.tensquare.article.config
 * @author:MartinKing
 * @createTime:2021/3/16 18:47
 * @version:1.0
 */
@Configuration
public class WebInterceptorConfig implements WebMvcConfigurer {
    @Autowired
    private JwtInterceptor jwtInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor).addPathPatterns("/**").excludePathPatterns("/login/**");
    }
}
