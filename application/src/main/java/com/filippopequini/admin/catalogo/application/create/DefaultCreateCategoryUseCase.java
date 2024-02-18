package com.filippopequini.admin.catalogo.application.create;

import com.filippopequini.admin.catalogo.domain.category.Category;
import com.filippopequini.admin.catalogo.domain.category.CategoryGateway;
import com.filippopequini.admin.catalogo.domain.validation.handler.Notification;
import io.vavr.API;
import io.vavr.control.Either;
import io.vavr.control.Try;

import java.util.Objects;

import static io.vavr.API.*;

public class DefaultCreateCategoryUseCase extends CreateCategoryUseCase {

    private final CategoryGateway categoryGateway;

    public DefaultCreateCategoryUseCase(CategoryGateway categoryGateway) {
        this.categoryGateway = Objects.requireNonNull(categoryGateway);
    }

    @Override
    public Either<Notification, CreateCategoryOutput> execute(final CreateCategoryCommand aCommand) {
        // ToDo copia defensiva
        final var aName = aCommand.name();
        final var aDescription = aCommand.description();
        final var isActive = aCommand.isActive();

        final var notification = Notification.create();
        final var aCategory = Category.newCategory(aName, aDescription, isActive);
        aCategory.validate(notification);

        return notification.hasErrors() ? Left(notification): create(aCategory);
    }

    private Either<Notification, CreateCategoryOutput> create(Category aCategory) {
        return Try(() -> this.categoryGateway.create(aCategory))
                .toEither()
                .bimap(Notification::create, CreateCategoryOutput::from);
    }
}
