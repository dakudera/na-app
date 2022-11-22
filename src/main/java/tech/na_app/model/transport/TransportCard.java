package tech.na_app.model.transport;

import com.fasterxml.jackson.annotation.JsonProperty;
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

    @JsonProperty("nomenclature_name")
    @Schema(
            example = "Tigach"
    )
    private String nomenclature_name;

    @JsonProperty("technical_certificate")
    private TechnicalCertificate technical_certificate;

    @JsonProperty("using_reason_info")
    private UsingReasonInfo using_reason_info;

    @JsonProperty("general_info")
    private GeneralInfo general_info;
}
