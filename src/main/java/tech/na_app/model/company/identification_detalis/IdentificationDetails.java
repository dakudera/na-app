package tech.na_app.model.company.identification_detalis;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IdentificationDetails {

    @Schema(example = "1231321")
    private String edrpou;
    @Schema(example = "certificate")
    private String registration_certificate;
    @Schema(example = "some ipm")
    private String ipn;
    @Schema(example = "some tax")
    private String accounting_tax_info;
    @Schema(example = "some form")
    private String tax_form;

}
