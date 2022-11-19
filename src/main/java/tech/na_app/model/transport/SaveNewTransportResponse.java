package tech.na_app.model.transport;

import lombok.Data;
import tech.na_app.model.ErrorObject;

@Data
public class SaveNewTransportResponse {

    private ErrorObject error;

    public SaveNewTransportResponse(ErrorObject error) {
        this.error = error;
    }
}
