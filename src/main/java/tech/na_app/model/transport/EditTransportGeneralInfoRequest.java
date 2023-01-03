package tech.na_app.model.transport;

import lombok.Data;

@Data
public class EditTransportGeneralInfoRequest {

    private Integer carId;
    private GeneralInfo general_info;

}
