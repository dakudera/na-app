package tech.na_app.services.vehicle.edit_data;

import tech.na_app.model.vehicle.using_reason.EditTransportUsingReasonInfoRequest;
import tech.na_app.model.vehicle.using_reason.EditTransportUsingReasonInfoResponse;

public interface EditTransportUsingReasonInfoService {

    EditTransportUsingReasonInfoResponse editTransportUsingReasonInfo(String requestId, EditTransportUsingReasonInfoRequest request);

}
