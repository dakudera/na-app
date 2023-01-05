package tech.na_app.model.transport.general_info;


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

    @Schema(
            example = "183256"
    )
    private BigDecimal mileage;

    @Schema(
            example = "145"
    )
    private BigDecimal fuel_tank_volume;

    @Schema(
            example = "3.26"
    )
    private BigDecimal height;

    @Schema(
            example = "2.67"
    )
    private BigDecimal width;

    @Schema(
            example = "15.69"
    )
    private BigDecimal length;
}
