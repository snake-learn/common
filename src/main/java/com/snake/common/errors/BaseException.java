package com.snake.common.errors;

public abstract class BaseException extends RuntimeException {
    public BaseException(String message) {
        super(message);
    }
}
