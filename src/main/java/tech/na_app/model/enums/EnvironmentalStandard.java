package tech.na_app.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EnvironmentalStandard {
    EURO_1("Євро-1"),
    EURO_2("Євро-2"),
    EURO_3("Євро-3"),
    EURO_4("Євро-4"),
    EURO_5("Євро-5"),
    EURO_6("Євро-6"),
    EURO_7("Євро-7"),
    EURO_8("Євро-8");

    private final String value;

}
