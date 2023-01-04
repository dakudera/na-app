package tech.na_app.services.transport;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import tech.na_app.converter.TransportConverter;
import tech.na_app.entity.transport.GeneralInfo;
import tech.na_app.entity.transport.Transport;
import tech.na_app.entity.transport.TransportCard;
import tech.na_app.entity.transport.TransportSequence;
import tech.na_app.entity.user.User;
import tech.na_app.model.ApiException;
import tech.na_app.model.ErrorObject;
import tech.na_app.model.transport.*;
import tech.na_app.repository.TransportRepository;
import tech.na_app.utils.SequenceGeneratorService;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

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

    public EditTransportGeneralInfoResponse editTransportGeneralInfo(String requestId, EditTransportGeneralInfoRequest request) {
        try {
            if (Objects.isNull(request)) {
                throw new ApiException(400, "BAD REQUEST");
            }
            if (Objects.isNull(request.getGeneral_info()) || Objects.isNull(request.getId())) {
                throw new ApiException(400, "BAD REQUEST");
            }

            Optional<Transport> transportOptional = transportRepository.findById(request.getId());
            Transport transport = transportOptional.orElseThrow(() -> new ApiException(404, "Not Found"));


            GeneralInfo buildGeneralInfo = GeneralInfo.builder()
                    .width(request.getGeneral_info().getWidth())
                    .height(request.getGeneral_info().getHeight())
                    .length(request.getGeneral_info().getLength())
                    .fuel_tank_volume(request.getGeneral_info().getFuel_tank_volume())
                    .mileage(request.getGeneral_info().getMileage())
                    .build();

            if (transport.getTransport_card() != null) {
                transport.getTransport_card().setGeneral_info(buildGeneralInfo);
            } else {
                TransportCard transport_card = TransportCard
                        .builder()
                        .general_info(buildGeneralInfo)
                        .build();
                transport.setTransport_card(transport_card);
            }
            transportRepository.save(transport);

            return new EditTransportGeneralInfoResponse(new ErrorObject(0));
        } catch (ApiException e) {
            log.error(requestId + " Error: " + e.getCode() + " Message: " + e.getMessage());
            return new EditTransportGeneralInfoResponse(new ErrorObject(e.getCode(), e.getMessage()));
        } catch (Exception e) {
            log.error(requestId + " Message: " + e.getMessage());
            return new EditTransportGeneralInfoResponse(new ErrorObject(500, "Something went wrong"));
        }
    }

    public GetTransportInfoResponse getTransportInfo(String requestId, User user, GetTransportInfoRequest request){
        try {
            if (Objects.isNull(request) || Objects.isNull(request.getId()) || Objects.isNull(user.getCompanyId())) {
                throw new ApiException(400, "BAD REQUEST");
            }

            Optional<Transport> transportOptional = transportRepository.findByIdAndCompanyId(request.getId(), user.getCompanyId());
            Transport transport = transportOptional.orElseThrow(() -> new ApiException(400, "BAD REQUEST"));

            return transportConverter.convertToTransportModel(transport);
        } catch (ApiException e) {
            log.error(requestId + " Error: " + e.getCode() + " Message: " + e.getMessage());
            return new GetTransportInfoResponse(new ErrorObject(e.getCode(), e.getMessage()));
        } catch (Exception e) {
            log.error(requestId + " Message: " + e.getMessage());
            return new GetTransportInfoResponse(new ErrorObject(500, "Something went wrong"));
        }
    }




}
