package tech.na_app.services.transport.get_data;

import tech.na_app.entity.user.User;
import tech.na_app.model.transport.GetAllTransportResponse;

public interface GetAllTransportService {

    GetAllTransportResponse getAllTransport(String requestId, User user);

}
