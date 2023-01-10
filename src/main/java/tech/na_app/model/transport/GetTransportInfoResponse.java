package tech.na_app.model.transport;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.na_app.model.ErrorObject;
import tech.na_app.model.transport.general_info.GeneralInfo;
import tech.na_app.model.transport.technical_certificate.TechnicalCertificate;
import tech.na_app.model.transport.using_reason.UsingReasonInfo;

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
