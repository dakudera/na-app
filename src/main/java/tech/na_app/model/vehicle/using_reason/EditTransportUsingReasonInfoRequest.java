package tech.na_app.model.vehicle.using_reason;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class EditTransportUsingReasonInfoRequest {

    @NotNull
    private String id;

    @NotNull
    private UsingReasonInfo using_reason_info;

}
