package com.macro.mall.common.api;

/**
 * 通用返回对象
 */
public class CommonResult<T> {
    private long code;
    private String message;
    private T data;

    /**
     * 普通成功返回
     *
     * @param data 获取的数据
     */
    public static <T> CommonResult<T> success(T data) {
        CommonResult<T> result = new CommonResult<T>();
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMsg());
        result.setData(data);
        return result;
    }

    /**
     * 普通成功返回
     *
     * @param data 获取的数据
     */
    public static <T> CommonResult<T> success(T data,String message) {
        CommonResult<T> result = new CommonResult<T>();
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(message);
        result.setData(data);
        return result;
    }

    /**
     * 通过错误码对象构造返回结果
     */
    public static <T> CommonResult<T> failed(IErrorCode errorCode) {
        CommonResult<T> result = new CommonResult<T>();
        result.setCode(errorCode.getCode());
        result.setMessage(errorCode.getMsg());
        return result;
    }

    /**
     * 普通失败提示信息
     */
    public static <T> CommonResult<T> failed(String message) {
        CommonResult<T> result = new CommonResult<T>();
        result.setCode(ResultCode.FAILED.getCode());
        result.setMessage(message);
        return result;
    }

    /**
     * 普通操作失败
     */
    public static <T> CommonResult<T> failed() {
        return failed(ResultCode.FAILED);
    }

    /**
     * 参数验证失败使用
     */
    public static <T> CommonResult<T> validateFailed() {
        return failed(ResultCode.VALIDATE_FAILED);
    }

    /**
     * 参数验证失败使用
     */
    public static <T> CommonResult<T> validateFailed(String message) {
        CommonResult<T> result = new CommonResult<T>();
        result.setCode(ResultCode.FAILED.getCode());
        result.setMessage(message);
        return result;
    }

    /**
     * 用户没有登录
     */
    public static <T> CommonResult<T> unauthorized() {
        return failed(ResultCode.UNAUTHORIZED);
    }

    /**
     * 用户没有相应权限
     */
    public static <T> CommonResult<T> forbidden() {
        return failed(ResultCode.UNAUTHORIZED);
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
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
