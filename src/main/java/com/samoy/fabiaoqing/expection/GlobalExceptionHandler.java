package com.samoy.fabiaoqing.expection;

import com.samoy.fabiaoqing.response.ApiResult;
import com.samoy.fabiaoqing.response.ResponseEnum;
import lombok.extern.log4j.Log4j2;
import org.mybatis.spring.MyBatisSystemException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
        return ApiResult.failure(exception.getCode(), exception.getMessage());
    }

    /**
     * 缺少参数异常处理
     *
     * @param request   请求
     * @param exception 缺少参数异常
     * @return json
     */
    @ExceptionHandler({ServletRequestBindingException.class, HttpMessageNotReadableException.class})
    public ApiResult paramExceptionHandler(HttpServletRequest request, Exception exception) {
        if (exception instanceof MissingServletRequestParameterException || exception instanceof HttpMessageNotReadableException) {
            return ApiResult.failure(ResponseEnum.PARAM_NOT_PRESENT);
        }
        return ApiResult.failure(ResponseEnum.UNKNOWN_ERROR);
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ApiResult mediaException(HttpServletRequest request, HttpMediaTypeNotSupportedException exception) {
        return ApiResult.failure(ResponseEnum.UNSUPPORTED_HTTP_TYPE);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ApiResult httpRequestMethodNotSupportedException(HttpServletRequest request, HttpRequestMethodNotSupportedException exception) {
        return ApiResult.failure(ResponseEnum.UNSUPPORTED_HTTP_REQUEST);
    }

    /**
     * 参数不合法异常处理
     *
     * @param request   请求
     * @param exception 参数不合法异常
     * @return json
     */
    @ExceptionHandler({MethodArgumentNotValidException.class, BindException.class})
    public ApiResult methodArgumentsHandler(HttpServletRequest request, Exception exception) {
        BindingResult bindingResult = null;
        if (exception instanceof MethodArgumentNotValidException) {
            bindingResult = ((MethodArgumentNotValidException) exception).getBindingResult();
        }
        if (exception instanceof BindException) {
            bindingResult = ((BindException) exception).getBindingResult();
        }
        List<FieldError> errors = Objects.requireNonNull(bindingResult).getFieldErrors();
        String messages = errors.stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining(","));
        return ApiResult.failure(ResponseEnum.PARAM_ILLEGAL.getCode(), messages);
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
        return ApiResult.failure(ResponseEnum.MYSQL_ERROR.getCode(), "服务器错误,请稍后再试");
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
        return ApiResult.failure(ResponseEnum.INTERNAL_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ApiResult commonException(HttpServletRequest request, Exception exception) {
        return ApiResult.failure(ResponseEnum.UNKNOWN_ERROR);
    }
}
