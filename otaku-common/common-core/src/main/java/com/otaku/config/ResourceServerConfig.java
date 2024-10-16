package com.otaku.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.otaku.constant.BusinessEnum;
import com.otaku.constant.HttpConstants;
import com.otaku.constant.ResourceConstants;
import com.otaku.filter.TokenTranslationFilter;
import com.otaku.model.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author: wz296
 * @Description: Spring Security安全框架资源服务器配置类
 * @Date: Created in 2024/10/16 下午1:27
 * @FileName: ResourceServerConfig
 * @Version: 1.0
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ResourceServerConfig extends WebSecurityConfigurerAdapter {

    @Autowired(required = false)
    private TokenTranslationFilter tokenTranslationFilter;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 关闭跨站请求
        http.csrf().disable();

        // 关闭跨域请求
        http.cors().disable();

        // 关闭Session策略
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // Token解析过滤器，将Token转换为Security能够认证的用户信息，存放到当前资源服务器的容器中
        http.addFilterBefore(tokenTranslationFilter, UsernamePasswordAuthenticationFilter.class);

        // 配置处理携带Token但权限不足的请求
        http.exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint()) // 处理没携带Token的请求
                .accessDeniedHandler(accessDeniedHandler()); // 处理Token过期或携带错误的Token或权限不足的Token

        // 配置其他请求
        http.authorizeHttpRequests()
                .antMatchers(ResourceConstants.RESOURCE_ALLOW_URLS)
                .permitAll()
                .anyRequest()
                .authenticated(); // 除了需要放行的请求，都得需要身份验证
    }

    /**
     * 请求没有携带Token
     * @return
     */
    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return (request, response, authException) -> {
            // 设置响应头
            response.setContentType(HttpConstants.APPLICATION_JSON);
            response.setCharacterEncoding(HttpConstants.CHARSET_UTF8);

            // 创建项目统一响应结果对象
            Result<Object> result = Result.fail(BusinessEnum.UN_AUTHORIZATION);
            ObjectMapper objectMapper = new ObjectMapper();
            String s = objectMapper.writeValueAsString(result);
            PrintWriter writer = response.getWriter();
            writer.write(s);
            writer.flush();
            writer.close();
        };
    }

    /**
     * 处理：请求携带Token但权限不足
     * @return
     */
    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return (request, response, accessDeniedException) -> {
            // 设置响应头信息
            response.setContentType(HttpConstants.APPLICATION_JSON);
            response.setCharacterEncoding(HttpConstants.CHARSET_UTF8);

            Result<Object> result = Result.fail(BusinessEnum.ACCESS_DENY_FAIL);
            ObjectMapper objectMapper = new ObjectMapper();
            String s = objectMapper.writeValueAsString(result);
            PrintWriter writer = response.getWriter();
            writer.write(s);
            writer.flush();
            writer.close();
        };
    }
}
