package com.filippopequini.admin.catalogo.domain.exceptions;

public abstract class NoStacktraceException extends RuntimeException {

    public NoStacktraceException(String message) {
        this(message, null);
    }

    public NoStacktraceException(final String message, final Throwable cause) {
        super(message, cause, true, false);
    }
}
