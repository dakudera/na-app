package tech.na_app.entity.profile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Profile {

    private String email;
    private String phone;
    private String fio;
    private String acc_order_number;

    @Temporal(TemporalType.DATE)
    private Date acc_order_date;
    private BigDecimal salary;

    @Temporal(TemporalType.DATE)
    private Date birthday;

    private Integer age;
    private String previous_work_exp;
    private String previous_info_work_mp;
    private String sufficient_experience_mp;
    private String registration_address;
    private String actual_address;

}
