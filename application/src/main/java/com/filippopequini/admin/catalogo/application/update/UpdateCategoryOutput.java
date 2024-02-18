package com.filippopequini.admin.catalogo.application.update;

import com.filippopequini.admin.catalogo.domain.category.Category;
import com.filippopequini.admin.catalogo.domain.category.CategoryID;

public record UpdateCategoryOutput(
        CategoryID id
) {

    public static UpdateCategoryOutput from(final Category aCategory) {
        return new UpdateCategoryOutput(aCategory.getId());
    }
}
