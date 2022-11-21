package tech.na_app.model.transport;


import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GeneralInfo {

    @JsonProperty("mileage")
    @Schema(
            example = "183256"
    )
    private BigDecimal mileage;

    @JsonProperty("fuel_tank_volume")
    @Schema(
            example = "145"
    )
    private BigDecimal fuel_tank_volume;

    @JsonProperty("height")
    @Schema(
            example = "3.26"
    )
    private BigDecimal height;

    @JsonProperty("width")
    @Schema(
            example = "2.67"
    )
    private BigDecimal width;

    @JsonProperty("length")
    @Schema(
            example = "15.69"
    )
    private BigDecimal length;
}
