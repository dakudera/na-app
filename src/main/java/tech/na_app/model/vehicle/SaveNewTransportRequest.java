package tech.na_app.model.vehicle;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class SaveNewTransportRequest {

    @NotNull
    private TransportCard transport_card;

}
