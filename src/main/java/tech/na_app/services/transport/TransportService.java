package tech.na_app.services.transport;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import tech.na_app.converter.TransportConverter;
import tech.na_app.entity.transport.Transport;
import tech.na_app.entity.transport.TransportSequence;
import tech.na_app.model.ApiException;
import tech.na_app.model.ErrorObject;
import tech.na_app.model.transport.SaveNewTransportRequest;
import tech.na_app.model.transport.SaveNewTransportResponse;
import tech.na_app.repository.TransportRepository;
import tech.na_app.services.company.CompanyService;
import tech.na_app.utils.SequenceGeneratorService;

import java.util.Objects;

@Log4j2
@Service
@RequiredArgsConstructor
public class TransportService {

    private final SequenceGeneratorService sequenceGeneratorService;
    private final TransportRepository transportRepository;
    private final CompanyService companyService;
    private final TransportConverter transportConverter;

    public SaveNewTransportResponse saveNewTransport(String requestId, SaveNewTransportRequest request) {
        try {
            if (Objects.isNull(request)) {
                throw new ApiException(400, "BAD_REQUEST");
            }
            if (Objects.isNull(request.getCompany_id())) {
                throw new ApiException(400, "BAD_REQUEST");
            }
            companyService.findById(request.getCompany_id());
            TransportSequence sequenceNumber = (TransportSequence) sequenceGeneratorService.getSequenceNumber(Transport.SEQUENCE_NAME, TransportSequence.class);
            transportRepository.save(transportConverter.convertToTransportEntity(request, sequenceNumber));
            return new SaveNewTransportResponse(new ErrorObject(0));
        } catch (ApiException e) {
            log.error(requestId + " Error: " + e.getCode() + " Message: " + e.getMessage());
            return new SaveNewTransportResponse(new ErrorObject(e.getCode(), e.getMessage()));
        }
    }

}
