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
     * 服务器内部异常
     */
    INTERNAL_ERROR(10002, "服务器内部异常"),
    /**
     * MySQL连接超时错误信息
     */
    MYSQL_ERROR(10003, "数据库异常"),
    /**
     * 缺少参数错误信息
     */
    PARAM_NOT_PRESENT(10004, "缺少参数"),
    /**
     * 参数不合法的错误信息
     */
    PARAM_ILLEGAL(10005, "参数不合法"),
    /**
     * 不支持的请求格式
     */
    UNSUPPORTED_HTTP_TYPE(10006, "不支持的请求类型"),
    /**
     * 不支持的请求方法
     */
    UNSUPPORTED_HTTP_REQUEST(10007, "不支持的请求方法"),
    /**
     * 上传文件不是图片的异常信息
     */
    NOT_IMAGE(10008, "您上传的文件不是图片"),
    /**
     * 文件上传失败异常信息
     */
    FILE_UPLOAD_FAILURE(10009, "文件上传失败"),
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
    /**
     * 手机号已被注册
     */
    TELEPHONE_EXISTS(60000, "手机号已被注册"),
    /**
     * 注册失败
     */
    REGISTER_FAILURE(60001, "注册失败"),
    /**
     * 用户不存在
     */
    USER_NOT_FOUND(60002, "用户不存在"),
    /**
     * 密码不匹配
     */
    PASSWORD_MISMATCH(60003, "密码错误"),
    /**
     * 缺失token
     */
    TOKEN_MISSING(60004, "token缺失"),
    /**
     * 登录过期
     */
    LOGIN_EXPIRED(60005, "登录已过期"),
    /**
     * 手机号为空
     */
    TELEPHONE_EMPTY(60006, "手机号不能为空"),
    /**
     * 密码为空
     */
    PASSWORD_EMPTY(60007, "密码不能为空"),
    /**
     * 登录失败
     */
    LOGIN_FAILURE(60008, "登录失败"),
    /**
     * 验证码为空
     */
    VERIFY_CODE_EMPTY(60009, "验证码为空"),
    /**
     * 修改资料失败
     */
    CHANGE_PROFILE_FAILURE(60010, "修改资料失败"),
    /**
     * 短信发送失败
     */
    SMS_SEND_FAILURE(70000, "短信发送失败"),
    /**
     * 短息验证失败
     */
    SMS_VERIFY_FAILURE(70001, "短信验证失败"),
    /**
     * 收藏失败
     */
    FAVORITE_ADD_FAILURE(80000, "收藏失败"),
    /**
     * 删除收藏失败
     */
    FAVORITE_DELETE_FAILURE(80001, "删除收藏失败"),
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
