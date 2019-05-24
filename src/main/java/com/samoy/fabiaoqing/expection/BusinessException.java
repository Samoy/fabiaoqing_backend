package com.samoy.fabiaoqing.expection;

import lombok.Getter;
import lombok.Setter;

/**
 * 业务异常
 *
 * @author Samoy
 * @date 2019-05-24
 */
public class BusinessException extends Exception {
    @Getter
    @Setter
    private Integer code;

    public BusinessException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}
