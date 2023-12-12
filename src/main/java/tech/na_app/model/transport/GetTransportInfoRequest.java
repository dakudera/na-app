package tech.na_app.model.transport;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class GetTransportInfoRequest {

    @NotNull
    private Integer id;

}
