package com.otaku.aspect;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.util.JSONPObject;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * @Author: wz296
 * @Description: 记录系统操作日志AOP
 * @Date: Created in 2024/10/30 上午9:14
 * @FileName: SysLogAspect
 * @Version: 1.0
 */
@Component
@Aspect
@Slf4j
public class SysLogAspect {

    /**
     * 切入点表达式
     */
    public static  final String POINT_CUT = "execution (* com.otaku.controller.*.*(..))";

    @Around(value = POINT_CUT)
    public Object logAround(ProceedingJoinPoint joinPoint) {
        Object result = null;
        // 获取请求对象
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        // 获取请求路径
        String requestURI = request.getRequestURI();
        // 获取IP地址
        String remoteHost = request.getRemoteHost();
        // 获取请求参数
        Object[] args = joinPoint.getArgs();
        // 获取请求方法
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        String methodName = method.toString();
        // 获取描述
        ApiOperation apiOperation = method.getDeclaredAnnotation(ApiOperation.class);
        String description = "";
        // 判断注解对象是否为空
        if (ObjectUtil.isNotNull(apiOperation)) {
            // 获取描述
            description = apiOperation.value();
        }
        String finalArgs = "";
        // 判断参数类型
        if (ObjectUtil.isNotNull(args) && args.length != 0 && args[0] instanceof MultipartFile) {
            // 说明当前参数为文件对象
            finalArgs = "file";
        } else {
            finalArgs = JSONObject.toJSONString(apiOperation);
        }
        // 记录开始时间
        long startTime = System.currentTimeMillis();
        // 执行方法
        try {
            result = joinPoint.proceed(args);
        } catch (Throwable throwable) {
            throw new RuntimeException(throwable);
        }
        // 记录结束时间
        long endTime = System.currentTimeMillis();

        // 执行时长
        long time = endTime - startTime;

        log.info("调用时间:{},请求接口路径:{},请求IP地址:{},方法名称:{},执行时长:{},方法描述:{}", new Date(), requestURI, remoteHost, methodName, time, description);

        return result;
    }
}
