package tech.na_app.model.company.identification_detalis;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EditIdentificationDetailsRequest {

    private IdentificationDetails identification_details;
}
