package tech.na_app.model.transport.technical_certificate_dop_info;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import tech.na_app.model.ErrorObject;

@Data
@Builder
@AllArgsConstructor
public class EditTechnicalCertificateDopInfoResponse {

    private ErrorObject error;
}
