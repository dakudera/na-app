package tech.na_app.model.transport;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.na_app.entity.transport.GeneralInfo;
import tech.na_app.entity.transport.TechnicalCertificate;
import tech.na_app.entity.transport.UsingReasonInfo;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransportCard {

    private String nomenclature_name;
    private TechnicalCertificate technical_certificate;
    private UsingReasonInfo using_reason_info;
    private GeneralInfo general_info;
}
