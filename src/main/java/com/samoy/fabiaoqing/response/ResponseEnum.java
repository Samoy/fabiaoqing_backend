package com.samoy.fabiaoqing.response;

import lombok.Getter;

/**
 * 返回结果枚举类
 * 1开头的除10000为成功返回外，其余为通用异常;
 * 2开头的为类别相关异常;
 * 3开头的为表情包相关异常;
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
     * 未知错误
     */
    UNKNOWN_ERROR(10001, "未知错误"),
    /**
     * 缺少参数错误信息
     */
    PARAM_NOT_PRESENT(10002, "缺少参数"),
    /**
     * MySQL连接超时错误信息
     */
    MYSQL_ERROR(10003, "数据库异常"),
    /**
     * 查询类别列表为空的错误信息
     */
    CATEGORY_NOT_FOUNT(20000, "未查询到任何类别"),
    /**
     * 类别id为空的错误信息
     */
    CATEGORY_ID_EMPTY(20001, "类别id不能为空"),
    /**
     * 表情包列表为空的错误信息
     */
    PACKAGE_NOT_FOUND(30000, "未查询到任何表情包"),
    /**
     * 表情包id为空的错误信息
     */
    PACKAGE_ID_EMPTY(30001, "表情包id不能为空"),
    /**
     * 表情列表为空的错误信息
     */
    EMOTICON_NOT_FOUNT(40000, "未查询到任何表情"),
    /**
     * 表情id为空的错误信息
     */
    EMOTICON_ID_EMPTY(40001, "表情id不能为空"),
    /**
     * 标签列表为空的错误信息
     */
    TAG_NOT_FOUND(50000, "未查询到任何标签"),
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