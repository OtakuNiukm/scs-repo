package com.otaku.filter;

import com.alibaba.fastjson.JSONObject;
import com.otaku.constant.AuthConstants;
import com.otaku.model.SecurityUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @Author: wz296
 * @Description: Token转换过滤器
 * @Date: Created in 2024/10/16 下午1:57
 * @FileName: TokenTranslationFilter
 * @Version: 1.0
 */
@Component
public class TokenTranslationFilter extends OncePerRequestFilter {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * Token转换过滤器
     * 前提：只负责处理携带Token的请求，然后将认证的用户信息转换出来
     * 没有携带Token的请求，交给security资源配置类中的处理器进行处理
     * <p>
     * 1. 获取Token
     * 2. 判断是否有值
     * 有：
     * Token转换为用户信息，
     * 并将用户信息转换为Security框架认识的用户信息对象，
     * 再将认识的用户信息对象存放在当前资源服务的窗口中
     *
     * @param request
     * @param response
     * @param filterChain
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 从请求头中获取Authorization的值，格式为bearer token
        String authorizationValue = request.getHeader("Authorization");
        // 判断是否为空
        if (authorizationValue != null && !authorizationValue.isEmpty()) {
            // 截取token，得到真正的token
            String token = authorizationValue.replaceFirst(AuthConstants.BEARER, "");
            // 判断Token值是否为空
            if (StringUtils.hasText(token)) {
                // 解决Token续签
                // 从Redis中获取Token的存活时长
                Long expire = stringRedisTemplate.getExpire(AuthConstants.LOGIN_TOKEN_PREFIX + token);
                // 判断是否超过系统指定的阈值
                if (expire < AuthConstants.TOKEN_EXPIRE_THRESHOLD_TIME) {
                    // 续签Token(增加Token在Redis中的存活时长)
                    stringRedisTemplate.expire(AuthConstants.LOGIN_TOKEN_PREFIX + token, AuthConstants.TOKEN_TIME, TimeUnit.SECONDS);
                }
                // 从Redis中获取json格式字符串
                String userJsonStr = stringRedisTemplate.opsForValue().get(AuthConstants.LOGIN_TOKEN_PREFIX + token);
                // 将json格式字符串的认证信息转换为认证用户对象
                SecurityUser securityUser = JSONObject.parseObject(userJsonStr, SecurityUser.class);
                // 处理权限
                Set<SimpleGrantedAuthority> collect = securityUser.getPerms().stream().map(SimpleGrantedAuthority::new).collect(Collectors.toSet());
                // 创建UsernamePasswordAuthenticationToken对象
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(securityUser, null, collect);
                // 将认证用户对象存放在当前模块的上下文中
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}
