package com.fetch.common.core.domain;

/**
 * @author jiang chen
 * @version 1.0
 * @description: TODO
 * @date 2024/3/10 20:56
 */
public class Result<D> {

    private Integer code;

    private String message;

    private D data;

    public Result() {
    }

    public Result(Integer code, String message, D data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Result(D data) {
        this.code = 200;
        this.message = "操作成功";
        this.data = data;
    }

    public Result(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Result<D> success() {
        this.code = 200;
        this.message = "操作成功";
        return this;
    }

    public Result<D> success(D data) {
        this.data = data;
        return this;
    }

    public Result<D> success(D data, String message) {
        this.data = data;
        this.message = message;
        return this;
    }

    public Result<D> failed(String message) {
        this.code = 500;
        this.message = message;
        return this;
    }

    public Result<D> failed(Integer code, String message) {
        this.code = code;
        this.message = message;
        return this;
    }
}
