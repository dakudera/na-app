package tech.na_app.model.vehicle.using_reason;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsingReasonInfo {

    @NotEmpty
    private String num_and_name_contract;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy", timezone = "Europe/Kiev")
    @Schema(
            example = "03.10.2022",
            pattern = "dd.MM.yyyy",
            type = "string"
    )
    @NotNull
    private Date date_start;

    @NotNull
    private Boolean is_contract_fixed_term;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy", timezone = "Europe/Kiev")
    @Schema(
            example = "03.10.2023",
            pattern = "dd.MM.yyyy",
            type = "string"
    )
    @NotNull
    private Date date_end;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy", timezone = "Europe/Kiev")
    @Schema(
            example = "03.10.2024",
            pattern = "dd.MM.yyyy",
            type = "string"
    )
    @NotNull
    private Date date_next_start;
}
