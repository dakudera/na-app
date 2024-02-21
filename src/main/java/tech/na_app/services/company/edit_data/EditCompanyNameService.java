package tech.na_app.services.company.edit_data;

import tech.na_app.entity.user.User;
import tech.na_app.model.company.conpany_name.EditCompanyNameRequest;
import tech.na_app.model.company.conpany_name.EditCompanyNameResponse;

public interface EditCompanyNameService {

    EditCompanyNameResponse editCompanyName(String requestId, User user, EditCompanyNameRequest request);

}
