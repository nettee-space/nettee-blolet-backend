package nettee.blolet.article.api.validation.context;

import nettee.common.validation.model.ValidationResponseModel;

public interface DraftCreateValidationSupplier {
    ValidationResponseModel getCreateValidationModel();
}
