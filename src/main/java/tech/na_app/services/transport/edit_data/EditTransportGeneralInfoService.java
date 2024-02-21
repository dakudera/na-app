package tech.na_app.services.transport.edit_data;

import tech.na_app.model.transport.general_info.EditTransportGeneralInfoRequest;
import tech.na_app.model.transport.general_info.EditTransportGeneralInfoResponse;

public interface EditTransportGeneralInfoService {

    EditTransportGeneralInfoResponse editTransportGeneralInfo(String requestId, EditTransportGeneralInfoRequest request);

}
