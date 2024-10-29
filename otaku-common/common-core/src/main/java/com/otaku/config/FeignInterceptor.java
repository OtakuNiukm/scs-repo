package com.otaku.config;

import cn.hutool.core.util.ObjectUtil;
import com.otaku.constant.AuthConstants;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: wz296
 * @Description: Fegin拦截器
 * 作用：解决服务之间调用无Token情况
 * 浏览器 -> A服务 -> B服务
 * 定时器 -> A服务
 * @Date: Created in 2024/10/16 下午2:41
 * @FileName: FeignInterceptor
 * @Version: 1.0
 */
public class FeignInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        // 获取当前请求的上下文对象
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        // 验空
        if (ObjectUtil.isNotNull(requestAttributes)) {
            HttpServletRequest request = requestAttributes.getRequest();
            // 判断是否有值
            if (ObjectUtil.isNotNull(request)) {
                // 获取请求头中的Token，传递到下一个请求的请求头中
                String authorization = request.getHeader(AuthConstants.AUTHORIZATION);
                requestTemplate.header(AuthConstants.AUTHORIZATION, authorization);
                return;
            }
        }
        requestTemplate.header(AuthConstants.AUTHORIZATION, AuthConstants.BEARER + "17ed7f2b-610b-4c88-953c-700a0e1d1050");
    }
}
