package tech.na_app.model.transport.technical_certificate_dop_info;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.na_app.model.enums.EnvironmentalStandard;
import tech.na_app.model.enums.Fuel;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TechnicalCertificateDopInfo {

    @Schema(
            example = "MAN"
    )
    private String brand;

    @Schema(
            example = "AE1111EA"
    )
    private String state_number;

    @Schema(
            example = "4Y1SL65848Z411439"
    )
    private String VIN_code;

    @Schema(
            example = "Black"
    )
    private String colour;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy", timezone = "Europe/Kiev")
    @Schema(
            example = "03.10.2022",
            pattern = "dd.MM.yyyy",
            type = "string"
    )
    private Date date_issue;

    @Schema(
            example = "3",
            type = "integer"
    )
    private Short seats;

    @Schema(
            example = "20145"
    )
    private BigDecimal full_weight;

    @Schema(
            example = "9930"
    )
    private BigDecimal empty_weight;

    private String category;

    private Fuel fuel;

    private String body_type;

    @Schema(
            example = "10518"
    )
    private BigDecimal engine_volume;

    @Schema(
            example = "324"
    )
    private BigDecimal engine_power;

    private EnvironmentalStandard environmental_standard;
}