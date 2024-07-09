package tech.na_app.model.vehicle;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class GetTransportInfoRequest {

    @NotNull
    private Integer id;

}
