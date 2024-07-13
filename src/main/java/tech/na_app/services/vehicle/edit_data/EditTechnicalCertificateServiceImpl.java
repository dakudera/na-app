package tech.na_app.services.vehicle.edit_data;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import tech.na_app.entity.transport.TechnicalCertificate;
import tech.na_app.entity.transport.Transport;
import tech.na_app.entity.transport.TransportCard;
import tech.na_app.model.exceptions.ApiException;
import tech.na_app.model.exceptions.ErrorObject;
import tech.na_app.model.vehicle.technical_certificate.EditTechnicalCertificateRequest;
import tech.na_app.model.vehicle.technical_certificate.EditTechnicalCertificateResponse;
import tech.na_app.repository.TransportRepository;

@Log4j2
@Service
@RequiredArgsConstructor
public class EditTechnicalCertificateServiceImpl implements EditTechnicalCertificateService {

    private final TransportRepository transportRepository;

    @Override
    public EditTechnicalCertificateResponse editTechnicalCertificate(String requestId, EditTechnicalCertificateRequest request) {
        try {
            Transport transport = transportRepository.findById(new ObjectId(request.getId()))
                    .orElseThrow(() -> new ApiException(404, "Not Found"));

            TechnicalCertificate buildTechnicalCertificate = TechnicalCertificate.builder()
                    .num_and_series(request.getTechnical_certificate().getNum_and_series())
                    .issued_by(request.getTechnical_certificate().getIssued_by())
                    .date_end(request.getTechnical_certificate().getDate_end())
                    .date_issue(request.getTechnical_certificate().getDate_issue())
                    .build();

            if (transport.getTransport_card() != null) {
                transport.getTransport_card().setTechnical_certificate(buildTechnicalCertificate);
            } else {
                TransportCard transport_card = TransportCard
                        .builder()
                        .technical_certificate(buildTechnicalCertificate)
                        .build();
                transport.setTransport_card(transport_card);
            }
            transportRepository.save(transport);

            return new EditTechnicalCertificateResponse(new ErrorObject(0));
        } catch (ApiException e) {
            log.error(requestId + " Error: " + e.getCode() + " Message: " + e.getMessage());
            return new EditTechnicalCertificateResponse(new ErrorObject(e.getCode(), e.getMessage()));
        } catch (Exception e) {
            log.error(requestId + " Message: " + e.getMessage());
            return new EditTechnicalCertificateResponse(new ErrorObject(500, "Something went wrong"));
        }
    }
}
