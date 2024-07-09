package tech.na_app.services.vehicle.save_data;

import tech.na_app.entity.user.User;
import tech.na_app.model.vehicle.SaveNewTransportRequest;
import tech.na_app.model.vehicle.SaveNewTransportResponse;

public interface SaveNewTransportService {

    SaveNewTransportResponse addNewVehicle(String requestId, SaveNewTransportRequest request, User user);

}
