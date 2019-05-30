package com.samoy.fabiaoqing.expection;

import com.samoy.fabiaoqing.response.ApiResult;
import com.samoy.fabiaoqing.response.ResponseEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
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
    /**
     * 业务异常处理
     *
     * @param request   请求
     * @param exception 业务异常
     * @return json
     */
    @ExceptionHandler(BusinessException.class)
    public ApiResult businessExceptionHandler(HttpServletRequest request, BusinessException exception) {
        return ApiResult.failure(exception.getCode(), exception.getMessage());
    }

    /**
     * 缺少参数异常处理
     *
     * @param request   请求
     * @param exception 缺少参数异常
     * @return json
     */
    @ExceptionHandler(ServletRequestBindingException.class)
    public ApiResult paramExceptionHandler(HttpServletRequest request, ServletRequestBindingException exception) {
        if (exception instanceof MissingServletRequestParameterException) {
            return ApiResult.failure(ResponseEnum.PARAM_NOT_PRESENT.getCode(), ResponseEnum.PARAM_NOT_PRESENT.getMessage());
        }
        return ApiResult.failure(ResponseEnum.UNKNOWN_ERROR.getCode(), ResponseEnum.UNKNOWN_ERROR.getMessage());
    }
}
