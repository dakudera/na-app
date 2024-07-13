package tech.na_app.services.vehicle.edit_data;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import tech.na_app.converter.TransportConverter;
import tech.na_app.entity.transport.Transport;
import tech.na_app.entity.transport.TransportCard;
import tech.na_app.entity.transport.UsingReasonInfo;
import tech.na_app.model.exceptions.ApiException;
import tech.na_app.model.exceptions.ErrorObject;
import tech.na_app.model.vehicle.using_reason.EditTransportUsingReasonInfoRequest;
import tech.na_app.model.vehicle.using_reason.EditTransportUsingReasonInfoResponse;
import tech.na_app.repository.TransportRepository;

import java.util.Optional;

@Log4j2
@Service
@RequiredArgsConstructor
public class EditTransportUsingReasonInfoServiceImpl implements EditTransportUsingReasonInfoService {

    private final TransportRepository transportRepository;
    private final TransportConverter transportConverter;

    @Override
    public EditTransportUsingReasonInfoResponse editTransportUsingReasonInfo(String requestId, EditTransportUsingReasonInfoRequest request) {
        try {

            Optional<Transport> transportOptional = transportRepository.findById(new ObjectId(request.getId()));
            Transport transport = transportOptional.orElseThrow(() -> new ApiException(404, "Not Found"));


            UsingReasonInfo buildUsingReasonInfo = UsingReasonInfo.builder()
                    .num_and_name_contract(request.getUsing_reason_info().getNum_and_name_contract())
                    .date_start(request.getUsing_reason_info().getDate_start())
                    .is_contract_fixed_term(request.getUsing_reason_info().getIs_contract_fixed_term())
                    .date_end(request.getUsing_reason_info().getDate_end())
                    .date_next_start(request.getUsing_reason_info().getDate_next_start())
                    .build();

            if (transport.getTransport_card() != null) {
                transport.getTransport_card().setUsing_reason_info(buildUsingReasonInfo);
            } else {
                TransportCard transport_card = TransportCard
                        .builder()
                        .using_reason_info(buildUsingReasonInfo)
                        .build();
                transport.setTransport_card(transport_card);
            }
            transportRepository.save(transport);

            return new EditTransportUsingReasonInfoResponse(new ErrorObject(0));
        } catch (ApiException e) {
            log.error(requestId + " Error: " + e.getCode() + " Message: " + e.getMessage());
            return new EditTransportUsingReasonInfoResponse(new ErrorObject(e.getCode(), e.getMessage()));
        } catch (Exception e) {
            log.error(requestId + " Message: " + e.getMessage());
            return new EditTransportUsingReasonInfoResponse(new ErrorObject(500, "Something went wrong"));
        }
    }
}
