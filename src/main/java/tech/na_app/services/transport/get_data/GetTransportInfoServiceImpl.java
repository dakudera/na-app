package tech.na_app.services.transport.get_data;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import tech.na_app.converter.TransportConverter;
import tech.na_app.entity.transport.Transport;
import tech.na_app.entity.user.User;
import tech.na_app.model.ApiException;
import tech.na_app.model.ErrorObject;
import tech.na_app.model.transport.GetTransportInfoRequest;
import tech.na_app.model.transport.GetTransportInfoResponse;
import tech.na_app.repository.TransportRepository;

import java.util.Objects;
import java.util.Optional;

@Log4j2
@Service
@RequiredArgsConstructor
public class GetTransportInfoServiceImpl implements GetTransportInfoService {

    private final TransportRepository transportRepository;
    private final TransportConverter transportConverter;


    @Override
    public GetTransportInfoResponse getTransportInfo(String requestId, User user, GetTransportInfoRequest request) {
        try {
            if (Objects.isNull(request) || Objects.isNull(request.getId()) || Objects.isNull(user.getCompanyId())) {
                throw new ApiException(400, "BAD REQUEST");
            }

            Optional<Transport> transportOptional = transportRepository.findByIdAndCompanyId(request.getId(), user.getCompanyId());
            Transport transport = transportOptional.orElseThrow(() -> new ApiException(400, "BAD REQUEST"));


            GetTransportInfoResponse response = transportConverter.convertToTransportModel(transport);
            response.setError(new ErrorObject(0));
            return response;
        } catch (ApiException e) {
            log.error(requestId + " Error: " + e.getCode() + " Message: " + e.getMessage());
            return new GetTransportInfoResponse(new ErrorObject(e.getCode(), e.getMessage()));
        } catch (Exception e) {
            log.error(requestId + " Message: " + e.getMessage());
            return new GetTransportInfoResponse(new ErrorObject(500, "Something went wrong"));
        }
    }
}
