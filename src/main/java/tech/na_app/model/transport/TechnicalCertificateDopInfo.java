package tech.na_app.model.transport;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import tech.na_app.model.enums.EnvironmentalStandard;
import tech.na_app.model.enums.Fuel;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class TechnicalCertificateDopInfo {

    @JsonProperty("brand")
    @Schema(
            example = "MAN"
    )
    private String brand;

    @JsonProperty("state_number")
    @Schema(
            example = "AE1111EA"
    )
    private String state_number;

    @JsonProperty("VIN_code")
    @Schema(
            example = "4Y1SL65848Z411439"
    )
    private String VIN_code;

    @JsonProperty("colour")
    @Schema(
            example = "Black"
    )
    private String colour;

    @JsonProperty("date_issue")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy", timezone = "Europe/Kiev")
    @Schema(
            example = "03.10.2022",
            pattern = "dd.MM.yyyy",
            type = "string"
    )
    private Date date_issue;

    @JsonProperty("seats")
    @Schema(
            example = "3",
            type = "integer"
    )
    private Short seats;

    @JsonProperty("full_weight")
    @Schema(
            example = "20145"
    )
    private BigDecimal full_weight;

    @JsonProperty("empty_weight")
    @Schema(
            example = "9930"
    )
    private BigDecimal empty_weight;

    @JsonProperty("category")
    private String category;

    @JsonProperty("fuel")
    private Fuel fuel;

    @JsonProperty("body_type")
    private String body_type;

    @JsonProperty("engine_volume")
    @Schema(
            example = "10518"
    )
    private BigDecimal engine_volume;

    @JsonProperty("engine_power")
    @Schema(
            example = "324"
    )
    private BigDecimal engine_power;

    @JsonProperty("environmental_standard")
    private EnvironmentalStandard environmental_standard;
}
