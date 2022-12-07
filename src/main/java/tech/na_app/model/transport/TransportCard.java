package tech.na_app.model.transport;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransportCard {

    @Schema(
            example = "Tigach"
    )
    private String nomenclature_name;

    private TechnicalCertificate technical_certificate;

    private UsingReasonInfo using_reason_info;

    private GeneralInfo general_info;
}
