package tech.na_app.model.transport;

import lombok.Data;
import tech.na_app.model.enums.TransportStatus;

@Data
public class SaveNewTransportRequest {

    private TransportCard transport_card;

}
