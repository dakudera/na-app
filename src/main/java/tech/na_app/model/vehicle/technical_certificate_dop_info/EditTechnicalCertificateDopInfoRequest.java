package tech.na_app.model.vehicle.technical_certificate_dop_info;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EditTechnicalCertificateDopInfoRequest {

    @NotNull
    private String id;

    @NotNull
    private TechnicalCertificateDopInfo technical_certificate_dop_info;

}
