package tech.na_app.services.vehicle.edit_data;

import tech.na_app.model.vehicle.technical_certificate_dop_info.EditTechnicalCertificateDopInfoRequest;
import tech.na_app.model.vehicle.technical_certificate_dop_info.EditTechnicalCertificateDopInfoResponse;

public interface EditTechnicalCertificateDopInfoService {

    EditTechnicalCertificateDopInfoResponse editTechnicalCertificateDopInfo(String requestId, EditTechnicalCertificateDopInfoRequest request);

}
