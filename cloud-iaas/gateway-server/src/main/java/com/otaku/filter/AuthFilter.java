package com.otaku.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.otaku.config.WhiteUrlsConfig;
import com.otaku.constant.AuthConstants;
import com.otaku.constant.BusinessEnum;
import com.otaku.constant.HttpConstants;
import com.otaku.model.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Date;

/**
 * 全局Token过滤器
 * 前后端约定的令牌存放位置： 请求头的Authorization bearer token
 */
@Component
@Slf4j
public class AuthFilter implements GlobalFilter, Ordered {

    @Autowired
    private WhiteUrlsConfig whiteUrlsConfig;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 校验Token
     * 1 获取请求路径
     * 2 判断是否放行
     * 2.1 放行： 不验证
     * 2.2 不放行： 验证
     *
     * @param exchange
     * @param chain
     * @return
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        // 获取请求对象
        ServerHttpRequest request = exchange.getRequest();
        // 获取请求路径
        String path = request.getPath().toString();
        // 判断是否需要放行，即是否存在于白名单中
        if (whiteUrlsConfig.getAllowUrls().contains(path)) {
            // 放行
            return chain.filter(exchange);
        }
        // 不包含在白名单中，对其进行身份认证
        // 从约定位置获取Token，值格式：bearer token
        String authorizationValue = request.getHeaders().getFirst(AuthConstants.AUTHORIZATION);

        if (StringUtils.hasText(authorizationValue)) {
            String tokenValue = authorizationValue.replaceFirst(AuthConstants.BEARER, "");
            if (StringUtils.hasText(tokenValue) && Boolean.TRUE.equals(stringRedisTemplate.hasKey(AuthConstants.LOGIN_TOKEN_PREFIX + tokenValue))) {
                return chain.filter(exchange);
            }
        }

        // 到此说明请求不合法或是身份认证不通过
        log.error("拦截非法请求，时间：{}，请求API路径：{}", new Date(), path);

        // 获取响应对象
        ServerHttpResponse response = exchange.getResponse();
        // 设置响应头信息
        response.getHeaders().set(HttpConstants.CONTENT_TYPE, HttpConstants.APPLICATION_JSON + ";" + HttpConstants.CHARSET_UTF8);

        // 设置响应消息
        Result<Object> result = Result.fail(BusinessEnum.UN_AUTHORIZATION);

        // 创建一个ObjectMapper对象
        ObjectMapper objectMapper = new ObjectMapper();
        byte[] bytes;
        try {
            bytes = objectMapper.writeValueAsBytes(result);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        DataBuffer dataBuffer = response.bufferFactory().wrap(bytes);

        return response.writeWith(Mono.just(dataBuffer));
    }

    @Override
    public int getOrder() {
        return -5;
    }
}
