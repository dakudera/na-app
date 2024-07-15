package tech.na_app.services.user_profile.edit_data;

import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

public class CalculateUserAge {

    private Date birthDay;

    public CalculateUserAge(Date birthDay) {
        this.birthDay = birthDay;
    }

    public Integer execute() {
        if (this.birthDay == null) {
            return 0;
        }

        LocalDate birthday = Instant.ofEpochMilli(this.birthDay.getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        LocalDate currentDate = Instant.ofEpochMilli(new Date().getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        if (currentDate != null) {
            return Period.between(birthday, currentDate).getYears();
        } else {
            return 0;
        }
    }

}
