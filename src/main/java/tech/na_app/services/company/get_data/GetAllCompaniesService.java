package tech.na_app.services.company.get_data;

import tech.na_app.model.company.GetAllCompanyResponse;

public interface GetAllCompaniesService {

    GetAllCompanyResponse getAllCompanies(String requestId);

}
