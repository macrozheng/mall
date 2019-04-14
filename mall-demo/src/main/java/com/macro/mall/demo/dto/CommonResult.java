package com.macro.mall.demo.dto;

/**
 * 通用返回对象
 */
public class CommonResult<T> {
    public static final int SUCCESS = 0;
    public static final int FAILED = 1;
    public static final int VALIDATE_FAILED = 2;
    private int code;
    private String message;
    private T data;

    /**
     * 普通成功返回
     *
     * @param data 获取的数据
     */
    public static <T> CommonResult<T> success(T data) {
        CommonResult<T> result = new CommonResult<T>();
        result.setCode(SUCCESS);
        result.setData(data);
        return result;
    }

    /**
     * 普通失败提示信息
     */
    public static <T> CommonResult<T> failed(String message) {
        CommonResult result = new CommonResult();
        result.setCode(FAILED);
        result.setMessage(message);
        return result;
    }

    /**
     * 参数验证失败使用
     *
     * @param message 错误信息
     */
    public static <T> CommonResult<T> validateFailed(String message) {
        CommonResult result = new CommonResult();
        result.setCode(VALIDATE_FAILED);
        result.setMessage(message);
        return result;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
