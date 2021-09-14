package com.jourwon.spring.boot.model;

/**
 * json 响应对象
 *
 * @author JourWon
 * @date 2021/9/14
 */
public class JsonResult {

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 数据
     */
    private Object data;

    /**
     * 信息
     */
    private String msg;

    /**
     * 正确时返回的信息
     */
    public static JsonResult isOk(Object data) {
        return new JsonResult(200, data, "success");
    }

    public static JsonResult isOk() {
        return new JsonResult(200, null, "success");
    }

    /**
     * 错误时返回的信息
     */
    public static JsonResult isError(Integer code, String msg) {
        return new JsonResult(code, null, msg);
    }

    public static JsonResult isError() {
        return new JsonResult(500, null, "");
    }

    public static JsonResult isError(String msg) {
        return new JsonResult(500, null, msg);
    }

    public JsonResult() {
        super();
    }

    public JsonResult(Integer code, Object data, String msg) {
        super();
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
