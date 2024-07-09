package tech.na_app.model.vehicle;

import lombok.Data;
import tech.na_app.model.exceptions.ErrorObject;

@Data
public class SaveNewTransportResponse {

    private ErrorObject error;

    public SaveNewTransportResponse(ErrorObject error) {
        this.error = error;
    }
}
