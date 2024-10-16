package com.otaku.ex.handler;

import com.otaku.constant.BusinessEnum;
import com.otaku.model.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Author: wz296
 * @Description:
 * @Date: Created in 2024/10/16 下午1:16
 * @FileName: GlobalExceptionHandle
 * @Version: 1.0
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandle {

    @ExceptionHandler(BusinessException.class)
    public Result<String> bussinessException(BusinessException e) {
        log.error(e.getMessage());
        return Result.fail(BusinessEnum.OPERATION_FAIL.getCode(), e.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public Result<String> runtimeException(RuntimeException e) {
        log.error(e.getMessage());
        return Result.fail(BusinessEnum.SERVER_INNER_ERROR);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public Result<String> accessDeniedException(AccessDeniedException e) {
        log.error(e.getMessage());
        throw e;
    }
}
