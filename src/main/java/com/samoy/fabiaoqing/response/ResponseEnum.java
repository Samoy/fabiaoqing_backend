package com.samoy.fabiaoqing.response;

import lombok.Getter;

/**
 * 返回结果枚举类
 *
 * @author Samoy
 * @date 2019-05-23
 */
public enum ResponseEnum {
    /**
     * 成功返回
     */
    SUCCESS(10000, "成功"),
    ;
    @Getter
    private Integer code;
    @Getter
    private String message;

    ResponseEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}