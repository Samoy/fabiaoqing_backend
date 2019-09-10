package com.samoy.fabiaoqing.security;

import com.samoy.fabiaoqing.expection.BusinessException;
import com.samoy.fabiaoqing.response.ResponseEnum;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * WebSecurityInterceptor
 *
 * @author Samoy
 * @date 2019/9/3
 */
@Component
public class WebSecurityInterceptor implements HandlerInterceptor {
    private static final String TOKEN_KEY = "token";

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws BusinessException {
        String token = request.getHeader(TOKEN_KEY);
        if (token == null) {
            //缺失token
            throw new BusinessException(ResponseEnum.TOKEN_MISSING);
        }
        String objectId = stringRedisTemplate.opsForValue().get(token);
        if (objectId == null) {
            //找不到objectId，说明登录已过期
            throw new BusinessException(ResponseEnum.LOGIN_EXPIRED);
        }
        return true;
    }
}
