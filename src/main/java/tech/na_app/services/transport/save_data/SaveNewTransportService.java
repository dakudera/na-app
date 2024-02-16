package tech.na_app.services.transport.save_data;

import tech.na_app.entity.user.User;
import tech.na_app.model.transport.SaveNewTransportRequest;
import tech.na_app.model.transport.SaveNewTransportResponse;

public interface SaveNewTransportService {

    SaveNewTransportResponse saveNewTransport(String requestId, SaveNewTransportRequest request, User user);

}
