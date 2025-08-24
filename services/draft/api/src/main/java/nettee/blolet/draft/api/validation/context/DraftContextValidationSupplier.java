package nettee.blolet.draft.api.validation.context;

import nettee.common.validation.model.ValidationResponseModel;

public interface DraftContextValidationSupplier extends DraftCreateValidationSupplier, DraftPatchValidationSupplier {
    ValidationResponseModel get(String context);
}
