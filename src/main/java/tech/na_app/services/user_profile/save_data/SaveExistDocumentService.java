package tech.na_app.services.user_profile.save_data;

import tech.na_app.entity.user.User;
import tech.na_app.model.profile.ExistDocumentRequest;
import tech.na_app.model.profile.ExistDocumentResponse;

public interface SaveExistDocumentService {

    ExistDocumentResponse saveExistDocument(String requestId, User user, ExistDocumentRequest request);

}
