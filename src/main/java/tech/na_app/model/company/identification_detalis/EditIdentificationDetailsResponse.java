package tech.na_app.model.company.identification_detalis;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.na_app.model.ErrorObject;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EditIdentificationDetailsResponse {

    private ErrorObject error;
}
