package tech.na_app.services.transport.edit_data;

import tech.na_app.model.transport.technical_certificate.EditTechnicalCertificateRequest;
import tech.na_app.model.transport.technical_certificate.EditTechnicalCertificateResponse;

public interface EditTechnicalCertificateService {

    EditTechnicalCertificateResponse editTechnicalCertificate(String requestId, EditTechnicalCertificateRequest request);

}
