package com.filippopequini.admin.catalogo.domain.category;


import com.filippopequini.admin.catalogo.domain.validation.ValidationHandler;
import com.filippopequini.admin.catalogo.domain.validation.Validator;
import com.filippopequini.admin.catalogo.domain.validation.Error;

public class CategoryValidator extends Validator {

    private static final int NAME_MAX_LENGTH = 255;
    private static final int NAME_MIN_LENGTH = 3;
    private final Category category;

    public CategoryValidator(final Category aCategory, final ValidationHandler aHandler) {
        super(aHandler);
        this.category = aCategory;
    }

    @Override
    public void validate() {
        checkNameConstraints();
    }

    private void checkNameConstraints() {
        final var name = this.category.getName();
        if(name == null){
            this.handler.append(new Error("'name' should not be null"));
            return;
        }
        if(name.isBlank()){
            this.handler.append(new Error("'name' should not be empty"));
            return;
        }

        final var length = name.trim().length();
        if(length < NAME_MIN_LENGTH || length > NAME_MAX_LENGTH){
            this.handler.append(new Error("'name' must be between 3 and 255 characters"));
            return;
        }

    }

}
