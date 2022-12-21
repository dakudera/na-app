package tech.na_app.model.profile;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import tech.na_app.model.enums.InternshipAndInstructionType;

import java.util.Date;

@Data
public class SaveInternshipRequest {

    private Integer id;
    @Schema(
            example = "1",
            type = "integer"
    )
    private Integer userId;
    @Schema(
            example = "123456",
            type = "string"
    )
    private String doc_number;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy", timezone = "Europe/Kiev")
    @Schema(
            example = "01.10.2015",
            pattern = "dd.MM.yyyy",
            type = "string"
    )
    private Date date;
    @Schema(
            example = "INTERNSHIP",
            type = "enum"
    )
    private InternshipAndInstructionType type;

}
