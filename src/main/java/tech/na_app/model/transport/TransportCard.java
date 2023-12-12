package tech.na_app.model.transport;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.na_app.model.transport.general_info.GeneralInfo;
import tech.na_app.model.transport.technical_certificate.TechnicalCertificate;
import tech.na_app.model.transport.using_reason.UsingReasonInfo;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransportCard {

    @Schema(
            example = "Tigach"
    )
    @NotEmpty
    private String nomenclature_name;

    @NotNull
    private TechnicalCertificate technical_certificate;

    @NotNull
    private UsingReasonInfo using_reason_info;

    @NotNull
    private GeneralInfo general_info;
}
