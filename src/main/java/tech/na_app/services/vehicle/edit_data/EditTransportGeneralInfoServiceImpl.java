package tech.na_app.services.vehicle.edit_data;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import tech.na_app.converter.TransportConverter;
import tech.na_app.entity.transport.GeneralInfo;
import tech.na_app.entity.transport.Transport;
import tech.na_app.entity.transport.TransportCard;
import tech.na_app.model.exceptions.ApiException;
import tech.na_app.model.exceptions.ErrorObject;
import tech.na_app.model.vehicle.general_info.EditTransportGeneralInfoRequest;
import tech.na_app.model.vehicle.general_info.EditTransportGeneralInfoResponse;
import tech.na_app.repository.TransportRepository;

import java.util.Objects;
import java.util.Optional;

@Log4j2
@Service
@RequiredArgsConstructor
public class EditTransportGeneralInfoServiceImpl implements EditTransportGeneralInfoService {

    private final TransportRepository transportRepository;
    private final TransportConverter transportConverter;


    @Override
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
}
