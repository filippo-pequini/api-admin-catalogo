package com.filippopequini.admin.catalogo.domain.exceptions;

import java.util.List;
import com.filippopequini.admin.catalogo.domain.validation.Error;

public class DomainException extends NoStacktraceException {

    private final List<Error> errors;

    private DomainException(final String aMessage, final List<Error> anErrors){
        super("");
        this.errors = anErrors;
    }

    public static DomainException with(final List<Error> anErrors) {
        return new DomainException("", anErrors);
    }

    public static DomainException with(final Error anError) {
        return new DomainException("", List.of(anError));
    }

    public List<Error> getErrors() {
        return errors;
    }
}
