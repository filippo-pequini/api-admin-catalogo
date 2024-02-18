package com.filippopequini.admin.catalogo.application.update;

import com.filippopequini.admin.catalogo.application.UseCase;
import com.filippopequini.admin.catalogo.domain.validation.handler.Notification;
import io.vavr.control.Either;

public abstract class UpdateCategoryUseCase extends UseCase<UpdateCategoryCommand, Either<Notification, UpdateCategoryOutput>> {


}
