package tech.na_app.services.user;

import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

@Component
public class UserHelperComponent {

    public Integer calculateAge(Date birthDate) {
        if (birthDate == null) {
            return 0;
        }

        LocalDate birthday = Instant.ofEpochMilli(birthDate.getTime())
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
