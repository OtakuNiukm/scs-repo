package com.otaku.config;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.otaku.constant.AuthConstants;
import com.otaku.constant.BusinessEnum;
import com.otaku.constant.HttpConstants;
import com.otaku.model.LoginResult;
import com.otaku.model.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import java.io.PrintWriter;
import java.time.Duration;
import java.util.UUID;

/**
 * Security安全框架配置类
 */
@Configuration
public class AuthSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * 设置Security安全框架走自己的认证流程
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 关闭跨站请求伪造
        http.cors().disable();
        // 关闭跨域请求
        http.csrf().disable();
        // 关闭Session使用策略
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // 配置登录信息
        http.formLogin()
                .loginProcessingUrl(AuthConstants.LOGIN_URL) // 设置登录URL
                .successHandler(authenticationSuccessHandler()) //设置登录成功处理器
                .failureHandler(authenticationFailureHandler()); // 设置登录失败处理器
        // 配置登出信息
        http.logout()
                .logoutUrl(AuthConstants.LOGOUT_URL) // 设置登出URL
                .logoutSuccessHandler(logoutSuccessHandler()); // 设置登出成功处理器
        // 所有请求都需要进行身份认证
        http.authorizeHttpRequests().anyRequest().authenticated();
    }

    /**
     * 登录成功处理器
     * @return
     */
    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return (request, response, authentication) -> {
            // 设置响应头信息
            response.setContentType(HttpConstants.CONTENT_TYPE);
            response.setCharacterEncoding(HttpConstants.CHARSET_UTF8);

            // 使用UUID来当作TOKEN
            String token = UUID.randomUUID().toString();
            // 从Security获取认证用户对象(SecurityUser)并转换为json格式的字符串
            String userJsonStr = JSONObject.toJSONString(authentication.getPrincipal());
            // 将token当作key，认证用户对象的json字符串Value放到redis中
            stringRedisTemplate.opsForValue().set(AuthConstants.LOGIN_TOKEN_PREFIX + token, userJsonStr, Duration.ofSeconds(AuthConstants.TOKEN_TIME));

            // 封装一个登录统一结果对象
            LoginResult loginResult = new LoginResult(token, AuthConstants.TOKEN_TIME);

            // 创建响应结果对象
            Result<Object> result = Result.success(loginResult);

            // 返回结果

            ObjectMapper objectMapper = new ObjectMapper();
            String s = objectMapper.writeValueAsString(result);
            PrintWriter printWriter = response.getWriter();
            printWriter.write(s);
            printWriter.flush();
            printWriter.close();
        };
    }

    /**
     * 登录失败处理器
     * @return
     */
    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return (request, response, exception) -> {
            // 设置响应头信息
            response.setContentType(HttpConstants.APPLICATION_JSON);
            response.setCharacterEncoding(HttpConstants.CHARSET_UTF8);

            // 创建统一响应结果对象
            Result<Object> result = new Result<>();
            result.setCode(BusinessEnum.OPERATION_FAIL.getCode());

            if (exception instanceof BadCredentialsException) {
                result.setMsg("用户名或密码有误");
            } else if (exception instanceof UsernameNotFoundException) {
                result.setMsg("用户不存在");
            } else if (exception instanceof AccountExpiredException) {
                result.setMsg("账号异常，请联系管理员");
            } else if (exception instanceof AccountStatusException) {
                result.setMsg("账号异常，请联系管理员");
            } else if (exception instanceof InternalAuthenticationServiceException) {
                result.setMsg(exception.getMessage());
            } else {
                result.setMsg(BusinessEnum.OPERATION_FAIL.getDesc());
            }

            // 返回结果
            ObjectMapper objectMapper = new ObjectMapper();
            String s = objectMapper.writeValueAsString(result);
            PrintWriter printWriter = response.getWriter();
            printWriter.write(s);
            printWriter.flush();
            printWriter.close();
        };
    }

    /**
     * 登出成功处理器
     * @return
     */
    @Bean
    public LogoutSuccessHandler logoutSuccessHandler() {
        return (request, response, authentication) -> {
            // 设置响应头信息
            response.setContentType(HttpConstants.APPLICATION_JSON);
            response.setCharacterEncoding(HttpConstants.CHARSET_UTF8);
            
            // 从请求头获取TOKEN
            String authorization = request.getHeader(AuthConstants.AUTHORIZATION);
            String token = authorization.replaceFirst(AuthConstants.BEARER, "");

            // 将当前Token从Redis中删除
            stringRedisTemplate.delete(AuthConstants.LOGIN_TOKEN_PREFIX + token);

            // 创建统一响应结果对象
            Result<Object> result = Result.success(null);

            // 返回结果
            ObjectMapper objectMapper = new ObjectMapper();
            String s = objectMapper.writeValueAsString(result);
            PrintWriter printWriter = response.getWriter();
            printWriter.write(s);
            printWriter.flush();
            printWriter.close();
        };
    }

    /**
     * 密码加密器
     * @return
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
