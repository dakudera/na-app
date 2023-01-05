package tech.na_app.model.transport;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

@Data
public class EditTransportUsingReasonInfoRequest {


    private Integer id;

    private UsingReasonInfo using_reason_info;

}
