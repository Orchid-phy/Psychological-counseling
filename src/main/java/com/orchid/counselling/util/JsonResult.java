package com.orchid.counselling.util;

/**
 * 返回数据格式
 * @param <T>
 */
public class JsonResult<T> {

    /**
     * 消息码
     */
    private Integer code;

    /**
     * 消息
     */
    private String msg;

    /**
     * 附带的数据
     */
    private T data;

    public JsonResult() {
    }

    public JsonResult(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public JsonResult(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
