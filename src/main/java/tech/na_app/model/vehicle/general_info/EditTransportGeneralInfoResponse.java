package tech.na_app.model.vehicle.general_info;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.na_app.model.exceptions.ErrorObject;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EditTransportGeneralInfoResponse {

    private ErrorObject error;

}
