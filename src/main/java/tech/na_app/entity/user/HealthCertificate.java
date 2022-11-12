package tech.na_app.entity.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HealthCertificate {

    private String certificate_number;

    @Temporal(TemporalType.DATE)
    private Date date_issue;

    @Temporal(TemporalType.DATE)
    private Date date_next_review;

}
