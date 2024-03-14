package com.fetch.common.exception;

/**
 * @author jiang chen
 * @version 1.0
 * @description: TODO
 * @date 2024/3/7 15:25
 */
public class IllegalParameterException extends RuntimeException {

    public IllegalParameterException(String message) {
        super(message);
    }

    public IllegalParameterException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalParameterException(Throwable cause) {
        super(cause);
    }
}
