package com.filippopequini.admin.catalogo.application.create;

import com.filippopequini.admin.catalogo.application.UseCase;
import com.filippopequini.admin.catalogo.domain.validation.handler.Notification;
import io.vavr.control.Either;

public abstract class CreateCategoryUseCase extends UseCase<CreateCategoryCommand, Either<Notification,CreateCategoryOutput>> {



}
