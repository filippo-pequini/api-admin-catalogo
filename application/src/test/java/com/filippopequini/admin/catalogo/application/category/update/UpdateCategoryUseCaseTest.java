package com.filippopequini.admin.catalogo.application.category.update;

import com.filippopequini.admin.catalogo.application.create.DefaultCreateCategoryUseCase;
import com.filippopequini.admin.catalogo.application.update.DefaultUpdateCategoryUseCase;
import com.filippopequini.admin.catalogo.application.update.UpdateCategoryCommand;
import com.filippopequini.admin.catalogo.domain.category.Category;
import com.filippopequini.admin.catalogo.domain.category.CategoryGateway;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Objects;
import java.util.Optional;

import static org.mockito.AdditionalAnswers.returnsFirstArg;

@ExtendWith(MockitoExtension.class)
public class UpdateCategoryUseCaseTest {

    @InjectMocks
    private DefaultUpdateCategoryUseCase useCase;
    @Mock
    private CategoryGateway categoryGateway;

    @Test
    public void givenAValidCommand_whenCallsUpdateCategory_shouldReturnCategoryId(){
        final var aCategory = Category.newCategory("Film", null, true);

        final var expectedName = "Filmes";
        final var expectedDescription = "A categoria mais assistida";
        final var expectedIsActive = true;

        final var expectedId = aCategory.getId();

        final var aCommand = UpdateCategoryCommand.with(
                expectedId.getValue(),
                expectedName,
                expectedDescription,
                expectedIsActive
        );

        Mockito.when(categoryGateway.findById(Mockito.eq(expectedId)))
                .thenReturn(Optional.of(aCategory));

        Mockito.when(categoryGateway.update(Mockito.any()))
                .thenAnswer(returnsFirstArg());

        final var actualOutput = useCase.execute(aCommand).get();

        Assertions.assertNotNull(actualOutput);
        Assertions.assertNotNull(actualOutput.id());

        Mockito.verify(categoryGateway, Mockito.times(1)).findById(expectedId);
        Mockito.verify(categoryGateway, Mockito.times(1)).update(Mockito.argThat(aUpdatedCategory -> {
            return Objects.equals(expectedName, aUpdatedCategory.getName())
                    && Objects.equals(expectedDescription, aUpdatedCategory.getDescription())
                    && Objects.equals(expectedIsActive, aUpdatedCategory.isActive())
                    && Objects.equals(expectedId, aUpdatedCategory.getId())
                    && Objects.equals(aCategory.getCreatedAt(), aUpdatedCategory.getCreatedAt())
                    && aCategory.getUpdatedAt().isBefore(aUpdatedCategory.getUpdatedAt())
                    && Objects.nonNull(aUpdatedCategory.getId())
                    && Objects.nonNull(aUpdatedCategory.getCreatedAt())
                    && Objects.nonNull(aUpdatedCategory.getUpdatedAt())
                    && Objects.isNull(aUpdatedCategory.getDeletedAt())
                    ;
        }));
    }
}
