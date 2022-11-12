package tech.na_app.model.enums;

public enum DriverLicenceCategory {

    A1(1),
    A(2),
    B1(3),
    B(4),
    C1(5),
    C(6),
    D1(7),
    D(8),
    C1E(9),
    BE(10),
    CE(11),
    D1E(12),
    DE(13),
    T1(14),
    T2(15);

    private Integer value;

    DriverLicenceCategory(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
