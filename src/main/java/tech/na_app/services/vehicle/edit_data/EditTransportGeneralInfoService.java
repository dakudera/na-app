package tech.na_app.services.vehicle.edit_data;

import tech.na_app.model.vehicle.general_info.EditTransportGeneralInfoRequest;
import tech.na_app.model.vehicle.general_info.EditTransportGeneralInfoResponse;

public interface EditTransportGeneralInfoService {

    EditTransportGeneralInfoResponse editTransportGeneralInfo(String requestId, EditTransportGeneralInfoRequest request);

}
