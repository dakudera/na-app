package tech.na_app.model.transport;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import tech.na_app.model.enums.EnvironmentalStandard;
import tech.na_app.model.enums.Fuel;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class TechnicalCertificateDopInfo {

    private String brand;
    private String state_number;
    private String VIN_code;
    private String colour;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy", timezone = "Europe/Kiev")
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
