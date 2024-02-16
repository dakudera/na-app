package tech.na_app.services.transport.get_data;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import tech.na_app.converter.TransportConverter;
import tech.na_app.entity.transport.Transport;
import tech.na_app.entity.user.User;
import tech.na_app.model.ErrorObject;
import tech.na_app.model.transport.GetAllTransportResponse;
import tech.na_app.repository.TransportRepository;

import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class GetAllTransportServiceImpl implements GetAllTransportService {

    private final TransportRepository transportRepository;
    private final TransportConverter transportConverter;

    @Override
    public GetAllTransportResponse getAllTransport(String requestId, User user) {
        try {
            List<Transport> allTransport = transportRepository.findAllByCompanyId(user.getCompanyId());
            if (allTransport.isEmpty()) {
                log.info(requestId + " Transport were not found");
                return new GetAllTransportResponse(new ErrorObject(0));
            }

            return GetAllTransportResponse.builder()
                    .transports(transportConverter.convertToTransports(allTransport))
                    .errorObject(new ErrorObject(0))
                    .build();
        } catch (Exception e) {
            log.error(requestId + " Error: " + 500 + " Message: " + e.getMessage());
            return new GetAllTransportResponse(new ErrorObject(500, e.getMessage()));
        }
    }
}
