package tech.na_app.services.vehicle.save_data;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import tech.na_app.converter.TransportConverter;
import tech.na_app.entity.user.User;
import tech.na_app.model.exceptions.ApiException;
import tech.na_app.model.exceptions.ErrorObject;
import tech.na_app.model.vehicle.SaveNewTransportRequest;
import tech.na_app.model.vehicle.SaveNewTransportResponse;
import tech.na_app.repository.TransportRepository;

@Log4j2
@Service
@RequiredArgsConstructor
public class SaveNewTransportServiceImpl implements SaveNewTransportService {

    private final TransportRepository transportRepository;
    private final TransportConverter transportConverter;

    @Override
    public SaveNewTransportResponse addNewVehicle(String requestId, SaveNewTransportRequest request, User user) {
        try {
            transportRepository.save(transportConverter.convertToTransportEntity(request, user));
            return new SaveNewTransportResponse(new ErrorObject(0));
        } catch (ApiException e) {
            log.error(requestId + " Error: " + e.getCode() + " Message: " + e.getMessage());
            return new SaveNewTransportResponse(new ErrorObject(e.getCode(), e.getMessage()));
        } catch (Exception e) {
            log.error(requestId + " Message: " + e.getMessage());
            return new SaveNewTransportResponse(new ErrorObject(500, "Something went wrong"));
        }
    }
}
