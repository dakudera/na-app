package tech.na_app.model.vehicle.general_info;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GeneralInfo {

    @Schema(
            example = "183256"
    )
    @NotNull
    private BigDecimal mileage;

    @Schema(
            example = "145"
    )
    @NotNull
    private BigDecimal fuel_tank_volume;

    @Schema(
            example = "3.26"
    )
    @NotNull
    private BigDecimal height;

    @Schema(
            example = "2.67"
    )
    @NotNull
    private BigDecimal width;

    @Schema(
            example = "15.69"
    )
    @NotNull
    private BigDecimal length;
}
