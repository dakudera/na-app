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

    @Schema(example = "с. Новоолександрівка, Дніпропетровська область")
    private String address;

    @Schema(example = "вул. Центральна, буд. 90, с. Новоолександрівка, Дніпровський район, Дн…")
    private String postal_address;

    private Communication communication;

    private BankingDetails banking_details;

}
