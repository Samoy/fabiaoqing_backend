package com.samoy.fabiaoqing.expection;

import com.samoy.fabiaoqing.response.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常处理
 *
 * @author Samoy
 * @date 2019-05-24
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(BusinessException.class)
    public ApiResult exceptionHandler(HttpServletRequest request, BusinessException exception) {
        return ApiResult.failure(exception.getCode(), exception.getMessage());
    }
}
