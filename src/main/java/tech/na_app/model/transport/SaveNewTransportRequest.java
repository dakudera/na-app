package tech.na_app.model.transport;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import tech.na_app.model.enums.TransportStatus;

@Data
public class SaveNewTransportRequest {

    @JsonProperty("transport_card")
    private TransportCard transport_card;

    @JsonProperty("transport_status")
    private TransportStatus transport_status;
}
