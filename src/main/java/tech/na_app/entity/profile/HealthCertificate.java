package tech.na_app.entity.profile;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HealthCertificate {


    @Schema(
            example = "134565543",
            type = "string"
    )
    private String certificate_number;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy", timezone = "Europe/Kiev")
    @Schema(
            example = "01.10.2003",
            pattern = "dd.MM.yyyy",
            type = "string"
    )
    @Temporal(TemporalType.DATE)
    private Date date_issue;

    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy", timezone = "Europe/Kiev")
    @Schema(
            example = "01.10.2013",
            pattern = "dd.MM.yyyy",
            type = "string"
    )
    private Date date_next_review;

}
