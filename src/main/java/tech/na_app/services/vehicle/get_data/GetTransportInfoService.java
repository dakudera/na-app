package tech.na_app.services.vehicle.get_data;

import tech.na_app.entity.user.User;
import tech.na_app.model.vehicle.GetTransportInfoRequest;
import tech.na_app.model.vehicle.GetTransportInfoResponse;

public interface GetTransportInfoService {

    GetTransportInfoResponse getTransportInfo(String requestId, User user, GetTransportInfoRequest request);

}
