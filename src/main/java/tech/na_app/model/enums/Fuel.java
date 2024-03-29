package tech.na_app.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Fuel {

    GASOLINE("Бензин"),
    DIESEL("Дизель"),
    GAS("Газ"),
    GAS_GASOLINE("Газ/Бензин"),
    HYBRID("Гібрид"),
    METHANE("Газ метан"),
    PROPANE_BUTANE("Газ пропан-бутан");

    private final String value;

}
