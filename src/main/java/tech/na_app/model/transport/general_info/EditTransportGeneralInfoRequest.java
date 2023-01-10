package tech.na_app.model.transport.general_info;

import lombok.Data;

@Data
public class EditTransportGeneralInfoRequest {

    private Integer id;
    private GeneralInfo general_info;

}
