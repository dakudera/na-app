package tech.na_app.services.transport.save_data;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import tech.na_app.converter.TransportConverter;
import tech.na_app.entity.transport.Transport;
import tech.na_app.entity.transport.TransportSequence;
import tech.na_app.entity.user.User;
import tech.na_app.model.ApiException;
import tech.na_app.model.ErrorObject;
import tech.na_app.model.transport.SaveNewTransportRequest;
import tech.na_app.model.transport.SaveNewTransportResponse;
import tech.na_app.repository.TransportRepository;
import tech.na_app.utils.SequenceGeneratorService;

@Log4j2
@Service
@RequiredArgsConstructor
public class SaveNewTransportServiceImpl implements SaveNewTransportService {

    private final SequenceGeneratorService sequenceGeneratorService;
    private final TransportRepository transportRepository;
    private final TransportConverter transportConverter;

    @Override
    public SaveNewTransportResponse saveNewTransport(String requestId, SaveNewTransportRequest request, User user) {
        try {
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
}
