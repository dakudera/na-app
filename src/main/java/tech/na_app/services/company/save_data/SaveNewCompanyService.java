package tech.na_app.services.company.save_data;

import tech.na_app.model.company.SaveNewCompanyRequest;
import tech.na_app.model.company.SaveNewCompanyResponse;

public interface SaveNewCompanyService {

    SaveNewCompanyResponse saveNewCompany(String requestId, SaveNewCompanyRequest request);

}
