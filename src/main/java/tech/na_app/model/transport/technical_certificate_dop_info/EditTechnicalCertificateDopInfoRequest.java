package tech.na_app.model.transport.technical_certificate_dop_info;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EditTechnicalCertificateDopInfoRequest {

    private Integer id;

    private TechnicalCertificateDopInfo technical_certificate_dop_info;
}
