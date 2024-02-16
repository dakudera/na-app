package tech.na_app.services.company.edit_data;

import tech.na_app.entity.user.User;
import tech.na_app.model.company.identification_detalis.EditIdentificationDetailsRequest;
import tech.na_app.model.company.identification_detalis.EditIdentificationDetailsResponse;

public interface EditIdentificationDetailsService {

    EditIdentificationDetailsResponse editIdentificationDetails(String requestId, User user, EditIdentificationDetailsRequest request);

}
