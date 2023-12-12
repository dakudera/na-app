package tech.na_app.model.company.identification_detalis;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IdentificationDetails {

    @Schema(example = "1231321")
    @NotEmpty
    private String edrpou;

    @Schema(example = "certificate")
    @NotEmpty
    private String registration_certificate;

    @Schema(example = "some ipm")
    @NotEmpty
    private String ipn;

    @Schema(example = "some tax")
    @NotEmpty
    private String accounting_tax_info;

    @Schema(example = "some form")
    @NotEmpty
    private String tax_form;

}
