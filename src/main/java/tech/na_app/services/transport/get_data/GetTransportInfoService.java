package tech.na_app.services.transport.get_data;

import tech.na_app.entity.user.User;
import tech.na_app.model.transport.GetTransportInfoRequest;
import tech.na_app.model.transport.GetTransportInfoResponse;

public interface GetTransportInfoService {

    GetTransportInfoResponse getTransportInfo(String requestId, User user, GetTransportInfoRequest request);

}
