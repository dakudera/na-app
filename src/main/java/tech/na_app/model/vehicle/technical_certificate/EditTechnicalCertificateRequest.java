package tech.na_app.model.vehicle.technical_certificate;

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
public class EditTechnicalCertificateRequest {

    @NotNull
    private Integer id;

    @NotNull
    private TechnicalCertificate technical_certificate;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TechnicalCertificate {

        @Schema(
                example = "CTX 915595"
        )
        @NotEmpty
        private String num_and_series;

        @Schema(
                example = "ABC 1249"
        )
        @NotEmpty
        private String issued_by;

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
                example = "03.10.2022",
                pattern = "dd.MM.yyyy",
                type = "string"
        )
        @NotNull
        private Date date_issue;
    }
}
