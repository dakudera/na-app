package tech.na_app.entity.transport;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.na_app.model.enums.EnvironmentalStandard;
import tech.na_app.model.enums.Fuel;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TechnicalCertificateDopInfo {

    private String brand;

    private String state_number;

    private String VIN_code;

    private String colour;

    @Temporal(value = TemporalType.DATE)
    private Date date_issue;

    private Short seats;

    private BigDecimal full_weight;

    private BigDecimal empty_weight;

    private String category;

    private Fuel fuel;

    private String body_type;

    private BigDecimal engine_volume;

    private BigDecimal engine_power;

    private EnvironmentalStandard environmental_standard;
}
