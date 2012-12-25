package ru.saloasdev.darts.exception;

@SuppressWarnings("serial")
public class BusinessException extends RuntimeException {
    
    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable cause) {
        super(message);
        initCause(cause);
    }

    @Override
    public String toString() {
        return getMessage();
    }
}
