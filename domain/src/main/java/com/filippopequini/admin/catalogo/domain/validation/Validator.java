package com.filippopequini.admin.catalogo.domain.validation;

public abstract class Validator {

    protected final ValidationHandler handler;

    protected Validator(final ValidationHandler aHandler) {
        this.handler = aHandler;
    }

    public abstract void validate();

    protected ValidationHandler getValidationHandler(){
        return this.handler;
    }

}
