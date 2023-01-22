package tech.na_app.model.company.company_global_info;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EditCompanyGlobalInfoRequest {

    @Schema(example = "s. Novooleksandrivka, Dnіpropetrovska oblast")
    private String address;

    @Schema(example = "vul. Centralna, bud. 90, s. Novooleksandrivka, Dnіprovskij rajon, Dnіpropetrovska oblast, Ukraina, 52070")
    private String postal_address;

    private Communication communication;

    private BankingDetails banking_details;

}
