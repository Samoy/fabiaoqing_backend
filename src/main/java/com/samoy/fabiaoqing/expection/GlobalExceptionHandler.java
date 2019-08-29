package com.samoy.fabiaoqing.expection;

import com.samoy.fabiaoqing.response.ApiResult;
import com.samoy.fabiaoqing.response.ResponseEnum;
import lombok.extern.log4j.Log4j2;
import org.mybatis.spring.MyBatisSystemException;
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
@Log4j2
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
        log.error("业务异常:{}", exception.getMessage());
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
        log.error("参数异常:{}", exception.getMessage());
        if (exception instanceof MissingServletRequestParameterException) {
            return ApiResult.failure(ResponseEnum.PARAM_NOT_PRESENT.getCode(), ResponseEnum.PARAM_NOT_PRESENT.getMessage());
        }
        return ApiResult.failure(ResponseEnum.UNKNOWN_ERROR.getCode(), ResponseEnum.UNKNOWN_ERROR.getMessage());
    }

    /**
     * 运行时异常处理
     *
     * @param request   请求
     * @param exception 运行时异常
     * @return json
     */
    @ExceptionHandler(RuntimeException.class)
    public ApiResult runtimeException(HttpServletRequest request, RuntimeException exception) {
        log.error("运行时异常:{}", exception.getMessage());
        return ApiResult.failure(ResponseEnum.INTERNAL_ERROR.getCode(), ResponseEnum.INTERNAL_ERROR.getMessage());
    }

    /**
     * 数据库异常处理
     *
     * @param request   请求
     * @param exception 数据库异常
     * @return json
     */
    @ExceptionHandler(MyBatisSystemException.class)
    public ApiResult dbExceptionHandler(HttpServletRequest request, MyBatisSystemException exception) {
        log.error("数据库异常:{}", exception.getMessage());
        return ApiResult.failure(ResponseEnum.MYSQL_ERROR.getCode(), "服务器错误,请稍后再试");
    }
}
