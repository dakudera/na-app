package tech.na_app.model.transport;

import lombok.Data;
import tech.na_app.entity.transport.TransportCard;

@Data
public class SaveNewTransportRequest {

    private Integer id;
    private TransportCard transport_card;
    private Integer company_id;
}
