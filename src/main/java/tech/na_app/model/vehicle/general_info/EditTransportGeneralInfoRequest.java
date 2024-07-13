package tech.na_app.model.vehicle.general_info;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class EditTransportGeneralInfoRequest {

    @NotNull
    private String id;

    @NotNull
    private GeneralInfo general_info;

}
