package com.tensquare.user.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * 安全配置类
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //authorize授权，authorizeRequests所有security全注解配置实现的开端，表示开始说明需要的权限
        //需要的权限两部分，一部分是拦截的路径，一部分是访问该路径需要的权限，
        //antMatchers表示拦截什么路径，permitAll任何权限都可以访问，直接放行所有
        //anyRequest() 任何的请求，authenticated认证后才能访问
        http
                .authorizeRequests()
                .antMatchers("/**").permitAll()
                .anyRequest().authenticated()
                .and().csrf().disable();
    }
}
