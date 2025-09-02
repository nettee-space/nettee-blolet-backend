package nettee.blolet.article.application.usecase;

import nettee.common.validation.model.ValidationResponseModel;

public interface DraftValidationResponseUseCase {
    ValidationResponseModel responseValidation(String context);
}
