package tech.na_app.services.company.get_data;

import tech.na_app.entity.user.User;
import tech.na_app.model.company.GetCompanyInfoResponse;

public interface GetCompanyInfoService {

    GetCompanyInfoResponse getCompanyInfo(String requestId, User user);

}
