package tech.na_app.services.vehicle.edit_data;

import tech.na_app.model.vehicle.technical_certificate.EditTechnicalCertificateRequest;
import tech.na_app.model.vehicle.technical_certificate.EditTechnicalCertificateResponse;

public interface EditTechnicalCertificateService {

    EditTechnicalCertificateResponse editTechnicalCertificate(String requestId, EditTechnicalCertificateRequest request);

}
