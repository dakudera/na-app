package tech.na_app.model.transport;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SaveNewTransportRequest {

    @JsonProperty("transport_card")
    private TransportCard transport_card;
}
