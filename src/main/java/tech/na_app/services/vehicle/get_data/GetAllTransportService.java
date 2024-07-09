package tech.na_app.services.vehicle.get_data;

import tech.na_app.entity.user.User;
import tech.na_app.model.vehicle.GetAllTransportResponse;

public interface GetAllTransportService {

    GetAllTransportResponse getAllTransport(String requestId, User user);

}
