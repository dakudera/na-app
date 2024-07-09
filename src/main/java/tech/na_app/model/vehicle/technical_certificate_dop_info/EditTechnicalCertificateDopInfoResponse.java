package tech.na_app.model.vehicle.technical_certificate_dop_info;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import tech.na_app.model.exceptions.ErrorObject;

@Data
@Builder
@AllArgsConstructor
public class EditTechnicalCertificateDopInfoResponse {

    private ErrorObject error;
}
