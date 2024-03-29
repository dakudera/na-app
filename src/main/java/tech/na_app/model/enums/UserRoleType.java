package tech.na_app.model.enums;

import lombok.Getter;

@Getter
public enum UserRoleType {

    SUPER_ADMIN(1),
    ADMIN(2),
    DIRECTOR(3),
    EXECUTIVE_DIRECTOR(4),
    CHIEF_ACCOUNTANT(5),
    MECHANIC(6),
    INSTRUCTOR_DRIVER(7),
    ROAD_SAFETY_SPECIALIST(8),
    DRIVER(9),
    CHECKER(10),
    WAREHOUSE_MANAGER(11),
    UNKNOWN(12);

    private final Integer value;

    UserRoleType(Integer value) {
        this.value = value;
    }
}
