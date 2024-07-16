package com.oi.blog.common;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 响应数据 - 通用
 * @author supan
 */
@Data
@NoArgsConstructor
public class Result<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 成功状态码
     */
    public static final int SUCCESS = 200;

    /**
     * 失败状态码
     */
    public static final int FAIL = 500;

    private int code;

    private String msg;

    private T data;

    /**
     * 返回操作成功的结果（无返回数据）
     */
    public static <T> Result<T> ok() {
        return restResult(null, SUCCESS, "操作成功");
    }

    /**
     * 返回操作成功的结果（带返回数据）
     */
    public static <T> Result<T> ok(T data) {
        return restResult(data, SUCCESS, "操作成功");
    }

    /**
     * 返回操作成功的结果（带自定义消息）
     */
    public static <T> Result<T> ok(String msg) {
        return restResult(null, SUCCESS, msg);
    }

    /**
     * 返回操作成功的结果（带自定义消息和返回数据）
     */
    public static <T> Result<T> ok(String msg, T data) {
        return restResult(data, SUCCESS, msg);
    }

    /**
     * 返回操作失败的结果（无返回数据）
     */
    public static <T> Result<T> fail() {
        return restResult(null, FAIL, "操作失败");
    }

    /**
     * 返回操作失败的结果（带自定义消息）
     */
    public static <T> Result<T> fail(String msg) {
        return restResult(null, FAIL, msg);
    }

    /**
     * 返回操作失败的结果（带返回数据）
     */
    public static <T> Result<T> fail(T data) {
        return restResult(data, FAIL, "操作失败");
    }

    /**
     * 返回操作失败的结果（带自定义消息和返回数据）
     */
    public static <T> Result<T> fail(String msg, T data) {
        return restResult(data, FAIL, msg);
    }

    /**
     * 返回自定义状态码和消息的结果
     */
    public static <T> Result<T> fail(int code, String msg) {
        return restResult(null, code, msg);
    }

    /**
     * 返回警告消息的结果
     */
    public static <T> Result<T> warn(String msg) {
        return restResult(null, HttpStatus.WARN, msg);
    }

    /**
     * 返回警告消息的结果（带返回数据）
     */
    public static <T> Result<T> warn(String msg, T data) {
        return restResult(data, HttpStatus.WARN, msg);
    }

    private static <T> Result<T> restResult(T data, int code, String msg) {
        Result<T> result = new Result<>();
        result.setCode(code);
        result.setData(data);
        result.setMsg(msg);
        return result;
    }

    /**
     * 判断返回结果是否为错误
     */
    public static <T> boolean isError(Result<T> result) {
        return !isSuccess(result);
    }

    /**
     * 判断返回结果是否为成功
     */
    public static <T> boolean isSuccess(Result<T> result) {
        return Result.SUCCESS == result.getCode();
    }
}
