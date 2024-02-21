package tech.na_app.services.transport.edit_data;

import tech.na_app.model.transport.using_reason.EditTransportUsingReasonInfoRequest;
import tech.na_app.model.transport.using_reason.EditTransportUsingReasonInfoResponse;

public interface EditTransportUsingReasonInfoService {

    EditTransportUsingReasonInfoResponse editTransportUsingReasonInfo(String requestId, EditTransportUsingReasonInfoRequest request);

}
