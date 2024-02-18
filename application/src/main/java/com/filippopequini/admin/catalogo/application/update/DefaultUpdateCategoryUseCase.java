package com.filippopequini.admin.catalogo.application.update;

import com.filippopequini.admin.catalogo.domain.category.Category;
import com.filippopequini.admin.catalogo.domain.category.CategoryGateway;
import com.filippopequini.admin.catalogo.domain.category.CategoryID;
import com.filippopequini.admin.catalogo.domain.exceptions.DomainException;
import com.filippopequini.admin.catalogo.domain.validation.Validator;
import com.filippopequini.admin.catalogo.domain.validation.handler.Notification;
import com.filippopequini.admin.catalogo.domain.validation.Error;
import io.vavr.API;
import io.vavr.control.Either;
import io.vavr.control.Try;

import java.util.Objects;
import java.util.function.Supplier;

import static io.vavr.API.*;

public class DefaultUpdateCategoryUseCase extends UpdateCategoryUseCase {

    private final CategoryGateway categoryGateway;

    public DefaultUpdateCategoryUseCase(CategoryGateway categoryGateway) {
        this.categoryGateway = Objects.requireNonNull(categoryGateway);
    }

    @Override
    public Either<Notification, UpdateCategoryOutput> execute(UpdateCategoryCommand aCommand) {
        final var anId = CategoryID.from(aCommand.id());
        final var aName = aCommand.name();
        final var aDescription = aCommand.description();
        final var isActive = aCommand.isActive();

        final var aCategory = this.categoryGateway.findById(anId)
                .orElseThrow(notFound(anId));

        final var notification = Notification.create();

        aCategory
                .update(aName, aDescription, isActive)
                .validate(notification)
        ;
        return notification.hasErrors() ? Left(notification) : update(aCategory);
    }

    private Either<Notification, UpdateCategoryOutput> update(Category aCategory) {
        return Try(() -> this.categoryGateway.update(aCategory))
                .toEither()
                .bimap(Notification::create, UpdateCategoryOutput::from)
            ;
    }

    private Supplier<DomainException> notFound(final CategoryID anId) {
        return () -> DomainException.with(
                new Error("Category with ID %s was not found".formatted(anId.getValue()))
        );
    }
}
