package com.filippopequini.admin.catalogo.domain.validation;

import com.filippopequini.admin.catalogo.domain.validation.Error;
import java.util.List;

// ToDo Pesquisar sobre interface fluente
public interface ValidationHandler {
    ValidationHandler append(Error anError);
    ValidationHandler append(ValidationHandler anHandler);

    ValidationHandler validate(Validation aValidation);

    List<Error> getErrors();

    default boolean hasErrors(){
        return getErrors() != null && !(getErrors().isEmpty());
    }

    default Error firstError(){
        return hasErrors() ? getErrors().get(0) : null;
    }

    public interface Validation {
        void validate();
    }

}
