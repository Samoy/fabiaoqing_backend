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
    /**
     * 未查询到任何类别
     */
    CATEGORY_NOT_FOUNT(20000, "未查询到任何类别");
    @Getter
    private Integer code;
    @Getter
    private String message;

    ResponseEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}