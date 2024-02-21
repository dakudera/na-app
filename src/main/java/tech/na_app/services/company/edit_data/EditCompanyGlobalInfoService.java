package tech.na_app.services.company.edit_data;

import tech.na_app.entity.user.User;
import tech.na_app.model.company.company_global_info.EditCompanyGlobalInfoRequest;
import tech.na_app.model.company.company_global_info.EditCompanyGlobalInfoResponse;

public interface EditCompanyGlobalInfoService {

    EditCompanyGlobalInfoResponse editCompanyGlobalInfo(String requestId, User user, EditCompanyGlobalInfoRequest request);

}
