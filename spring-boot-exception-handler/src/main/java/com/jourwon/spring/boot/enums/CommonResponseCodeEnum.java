package com.jourwon.spring.boot.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 响应编码,参照阿里巴巴Java开发手册
 * 在无法更加具体确定的错误场景中，可以直接使用一级宏观错误码，
 * 分别是：00000（一切 ok）、A0001（用户端错误）、B0001（系统执行出错）、C0001（调用第三方服务出错）。
 *
 * @author JourWon
 * @date 2021/1/21
 */
@Getter
@AllArgsConstructor
public enum CommonResponseCodeEnum {

    /**
     * 成功
     */
    SUCCESS("00000", "成功"),

    /**
     * 用户请求参数错误
     */
    REQUEST_PARAMETER_ILLEGAL("A0400", "用户请求参数错误"),
    /**
     * 访问未授权
     */
    UNAUTHORIZED_ACCESS("A0301", "访问未授权"),
    /**
     * 不支持当前请求类型
     */
    NONSUPPORT_REQUEST_TYPE("A0444", "不支持当前请求类型"),
    /**
     * 用户id不存在
     */
    USER_ID_NOT_EXIST("A0445", "用户id不存在"),

    /**
     * 系统执行出错
     */
    SYS_EXCEPTION("B0001", "系统执行出错"),

    /**
     * 系统执行超时
     */
    SYSTEM_EXECUTION_TIMEOUT("B0100", "系统执行超时"),
    /**
     * 数据库字段重复
     */
    DATABSE_FIELD_DUPLICATE("A0446", "数据库字段重复"),
    ;

    /**
     * 响应编码
     */
    private final String code;

    /**
     * 响应信息
     */
    private final String message;

}
