package tech.na_app.model.vehicle.technical_certificate;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.na_app.model.exceptions.ErrorObject;

@Data
@Builder
@NoArgsConstructor
public class EditTechnicalCertificateResponse {

    private ErrorObject error;

    public EditTechnicalCertificateResponse(ErrorObject error) {
        this.error = error;
    }
}
