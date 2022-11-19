package tech.na_app.entity.transport_card;

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

    private BigDecimal mileage;

    private BigDecimal fuel_tank_volume;

    private BigDecimal height;

    private BigDecimal width;

    private BigDecimal length;

}
