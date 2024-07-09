package tech.na_app.model.vehicle;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.na_app.model.exceptions.ErrorObject;
import tech.na_app.model.vehicle.general_info.GeneralInfo;
import tech.na_app.model.vehicle.technical_certificate.TechnicalCertificate;
import tech.na_app.model.vehicle.using_reason.UsingReasonInfo;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetTransportInfoResponse {

    private String nomenclature_name;

    private TechnicalCertificate technical_certificate;

    private UsingReasonInfo using_reason_info;

    private GeneralInfo general_info;

    private ErrorObject error;

    public GetTransportInfoResponse(ErrorObject error) {
        this.error = error;
    }
}
