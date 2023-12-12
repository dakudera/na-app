package tech.na_app.model.company.company_global_info;


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
public class BankingDetails {

    @Schema(example = "UA793073500000037398800001488")
    @NotEmpty
    private String iban;

    @Schema(example = "PrivatBank")
    @NotEmpty
    private String remittance_bank;

}
