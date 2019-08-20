package com.competition.exception;

/**
 * 数据格式异常
 */
public class MyIllegalFormatException extends Exception {
    @Override
    public String getMessage() {
        return "can't accept the value of the format";
    }
}
