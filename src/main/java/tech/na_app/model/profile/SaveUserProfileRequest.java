package tech.na_app.model.profile;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import tech.na_app.entity.profile.AvailableDocuments;
import tech.na_app.entity.profile.DrivingLicense;
import tech.na_app.entity.profile.InternshipAndInstruction;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class SaveUserProfileRequest {

    @Schema(
            title = " user id",
            example = "1"
    )
    private Integer id;

    @Schema(
            example = "test@gmail.com"
    )
    private String email;


    @Schema(
            example = "Petro Ejik Perdole"
    )
    private String fio;

    @Schema(
            example = "1231231"
    )
    private String acc_order_number;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy", timezone = "Europe/Kiev")
    @Schema(
            example = "03.10.2023",
            pattern = "dd.MM.yyyy",
            type = "string"
    )
    private Date acc_order_date;

    @Schema(
            example = "20000.58",
            type = "number"
    )
    private BigDecimal salary;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy", timezone = "Europe/Kiev")
    @Schema(
            example = "01.10.1993",
            pattern = "dd.MM.yyyy",
            type = "string"
    )
    private Date birthday;

    @Schema(
            example = "10",
            type = "string"
    )
    private String previous_work_exp;

    @Schema(
            example = "12",
            type = "string"
    )
    private String previous_info_work_mp;

    @Schema(
            example = "12",
            type = "string"
    )
    private String sufficient_experience_mp;

    @Schema(
            example = "Dnipro city, Faina street",
            type = "string"
    )
    private String registration_address;

    @Schema(
            example = "Dnipro city, Faina street",
            type = "string"
    )
    private String actual_address;
    private DrivingLicense driving_license;
    private AvailableDocuments available_documents;
    private InternshipAndInstruction internship;

}
