package tech.na_app.model.transport;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TechnicalCertificate {

    @JsonProperty("num_and_series")
    @Schema(
            example = "CTX 915595"
    )
    private String num_and_series;

    @JsonProperty("issued_by")
    @Schema(
            example = "ABC 1249"
    )
    private String issued_by;

    @JsonProperty("date_end")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy", timezone = "Europe/Kiev")
    @Schema(
            example = "03.10.2023",
            pattern = "dd.MM.yyyy",
            type = "string"
    )
    private Date date_end;

    @JsonProperty("date_issue")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy", timezone = "Europe/Kiev")
    @Schema(
            example = "03.10.2022",
            pattern = "dd.MM.yyyy",
            type = "string"
    )
    private Date date_issue;

    @JsonProperty("technical_certificate_dop_info")
    private TechnicalCertificateDopInfo technical_certificate_dop_info;
}
