package tech.na_app.services.transport;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import tech.na_app.converter.TransportConverter;
import tech.na_app.entity.transport.Transport;
import tech.na_app.entity.transport.TransportSequence;
import tech.na_app.entity.user.User;
import tech.na_app.model.ApiException;
import tech.na_app.model.ErrorObject;
import tech.na_app.model.transport.GetAllTransportResponse;
import tech.na_app.model.transport.SaveNewTransportRequest;
import tech.na_app.model.transport.SaveNewTransportResponse;
import tech.na_app.repository.TransportRepository;
import tech.na_app.utils.SequenceGeneratorService;

import java.util.List;
import java.util.Objects;

@Log4j2
@Service
@RequiredArgsConstructor
public class TransportService {

    private final SequenceGeneratorService sequenceGeneratorService;
    private final TransportRepository transportRepository;
    private final TransportConverter transportConverter;

    public SaveNewTransportResponse saveNewTransport(String requestId, SaveNewTransportRequest request, User user) {
        try {
            if (Objects.isNull(request)) {
                throw new ApiException(400, "BAD_REQUEST");
            }
            TransportSequence sequenceNumber = (TransportSequence) sequenceGeneratorService.getSequenceNumber(Transport.SEQUENCE_NAME, TransportSequence.class);
            transportRepository.save(transportConverter.convertToTransportEntity(request, sequenceNumber, user));
            return new SaveNewTransportResponse(new ErrorObject(0));
        } catch (ApiException e) {
            log.error(requestId + " Error: " + e.getCode() + " Message: " + e.getMessage());
            return new SaveNewTransportResponse(new ErrorObject(e.getCode(), e.getMessage()));
        } catch (Exception e) {
            log.error(requestId + " Message: " + e.getMessage());
            return new SaveNewTransportResponse(new ErrorObject(500, "Something went wrong"));
        }
    }

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
