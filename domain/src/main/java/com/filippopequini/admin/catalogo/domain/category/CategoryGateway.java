package com.filippopequini.admin.catalogo.domain.category;

import com.filippopequini.admin.catalogo.domain.pagination.Pagination;

import java.util.Optional;

public interface CategoryGateway {

    Category create(Category aCategory);
    Category update(Category aCategory);
    void deleteById(CategoryID aCategoryId);
    Optional<Category> findById(CategoryID aCategoryId);
    Pagination<Category> findAll(CategorySearchQuery aQuery);
}
