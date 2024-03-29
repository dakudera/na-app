package tech.na_app.model.transport.general_info;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class EditTransportGeneralInfoRequest {

    @NotNull
    private Integer id;

    @NotNull
    private GeneralInfo general_info;

}
