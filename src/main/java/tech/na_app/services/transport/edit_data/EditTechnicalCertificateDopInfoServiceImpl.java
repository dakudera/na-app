package tech.na_app.services.transport.edit_data;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import tech.na_app.converter.TransportConverter;
import tech.na_app.entity.transport.TechnicalCertificate;
import tech.na_app.entity.transport.TechnicalCertificateDopInfo;
import tech.na_app.entity.transport.Transport;
import tech.na_app.entity.transport.TransportCard;
import tech.na_app.model.ApiException;
import tech.na_app.model.ErrorObject;
import tech.na_app.model.transport.technical_certificate_dop_info.EditTechnicalCertificateDopInfoRequest;
import tech.na_app.model.transport.technical_certificate_dop_info.EditTechnicalCertificateDopInfoResponse;
import tech.na_app.repository.TransportRepository;

@Log4j2
@Service
@RequiredArgsConstructor
public class EditTechnicalCertificateDopInfoServiceImpl implements EditTechnicalCertificateDopInfoService {

    private final TransportRepository transportRepository;
    private final TransportConverter transportConverter;

    @Override
    public EditTechnicalCertificateDopInfoResponse editTechnicalCertificateDopInfo(String requestId, EditTechnicalCertificateDopInfoRequest request) {
        try {
            Transport transport = transportRepository.findById(request.getId())
                    .orElseThrow(() -> new ApiException(404, "Not Found"));

            TechnicalCertificateDopInfo buildTechnicalCertificateDopInfo = transportConverter.convertToTechnicalCertificateDopInfoEntity(request);

            if (transport.getTransport_card() != null && transport.getTransport_card().getTechnical_certificate() != null) {
                transport.getTransport_card().getTechnical_certificate().setTechnical_certificate_dop_info(buildTechnicalCertificateDopInfo);
            } else {
                TransportCard transport_card = TransportCard
                        .builder()
                        .technical_certificate(TechnicalCertificate.builder()
                                .technical_certificate_dop_info(buildTechnicalCertificateDopInfo)
                                .build())
                        .build();
                transport.setTransport_card(transport_card);
            }
            transportRepository.save(transport);

            return new EditTechnicalCertificateDopInfoResponse(new ErrorObject(0));
        } catch (ApiException e) {
            log.error(requestId + " Error: " + e.getCode() + " Message: " + e.getMessage());
            return new EditTechnicalCertificateDopInfoResponse(new ErrorObject(e.getCode(), e.getMessage()));
        } catch (Exception e) {
            log.error(requestId + " Message: " + e.getMessage());
            return new EditTechnicalCertificateDopInfoResponse(new ErrorObject(500, "Something went wrong"));
        }
    }
}
