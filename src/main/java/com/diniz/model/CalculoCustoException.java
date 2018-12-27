package com.diniz.model;

@SuppressWarnings("serial")
public class CalculoCustoException extends RuntimeException {

    public CalculoCustoException(final String msg) {
        super(msg);
    }

    public CalculoCustoException(final Throwable cause) {
        super(cause);
    }

    public CalculoCustoException(String message, Throwable cause) {
        super(message, cause);
    }
}