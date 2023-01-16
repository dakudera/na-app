package tech.na_app.model.company;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.na_app.model.ErrorObject;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EditCompanyNameResponse {

    private ErrorObject error;

}
