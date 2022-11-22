package tech.na_app.model.profile;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import tech.na_app.entity.profile.AvailableDocuments;
import tech.na_app.entity.profile.DrivingLicense;
import tech.na_app.entity.profile.Education;
import tech.na_app.entity.profile.Internship;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class SaveUserProfileRequest {

    private Integer id;
    private String email;
    private String fio;
    private String acc_order_number;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy", timezone = "Europe/Kiev")
    @Schema(
            example = "03.10.2023",
            pattern = "dd.MM.yyyy",
            type = "string"
    )
    private Date acc_order_date;
    private BigDecimal salary;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy", timezone = "Europe/Kiev")
    private Date birthday;
    private String previous_work_exp;
    private String previous_info_work_mp;
    private String sufficient_experience_mp;
    private String registration_address;
    private String actual_address;
    private Education education;
    private DrivingLicense driving_license;
    private AvailableDocuments available_documents;
    private Internship internship;

}
