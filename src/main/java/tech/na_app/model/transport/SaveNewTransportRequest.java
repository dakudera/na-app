package tech.na_app.model.transport;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class SaveNewTransportRequest {

    @NotNull
    private TransportCard transport_card;

}
