package tech.na_app.model.company;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IdentificationDetails {

    private String edrpou;
    private String registration_certificate;
    private String ipn;
    private String accounting_tax_info;
    private String tax_form;

}
