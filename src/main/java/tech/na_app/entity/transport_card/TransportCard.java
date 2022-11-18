package tech.na_app.entity.transport_card;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
