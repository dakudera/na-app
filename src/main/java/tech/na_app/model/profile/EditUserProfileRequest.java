package tech.na_app.model.profile;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.na_app.model.enums.UserRoleType;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EditUserProfileRequest {

    @Schema(
            example = "1",
            title = " user id"
    )
    @NotNull
    private String id;

    @NotNull
    private UserRoleType role;

    @Schema(
            example = "test@gmail.com"
    )
    @NotEmpty
    private String email;

    @Schema(
            example = "+38012345678"
    )
    @NotEmpty
    private String phone;

    @Schema(
            example = "Petro Ejik Perdole"
    )
    @NotEmpty
    private String fio;

    @Schema(
            example = "1231231"
    )
    @NotEmpty
    private String acc_order_number;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy", timezone = "Europe/Kiev")
    @Schema(
            example = "03.10.2023",
            pattern = "dd.MM.yyyy",
            type = "string"
    )
    @NotNull
    private Date acc_order_date;

    @Schema(
            example = "20000.58",
            type = "number"
    )
    @NotNull
    private BigDecimal salary;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy", timezone = "Europe/Kiev")
    @Schema(
            example = "01.10.1993",
            pattern = "dd.MM.yyyy",
            type = "string"
    )
    @NotNull
    private Date birthday;

    @Schema(
            example = "10",
            type = "string"
    )
    @NotEmpty
    private String previous_work_exp;

    @Schema(
            example = "12",
            type = "string"
    )
    @NotEmpty
    private String previous_info_work_mp;

    @Schema(
            example = "12",
            type = "string"
    )
    @NotEmpty
    private String sufficient_experience_mp;

    @Schema(
            example = "Dnipro city, Faina street",
            type = "string"
    )
    @NotEmpty
    private String registration_address;

    @Schema(
            example = "Dnipro city, Faina street",
            type = "string"
    )
    @NotEmpty
    private String actual_address;

}
