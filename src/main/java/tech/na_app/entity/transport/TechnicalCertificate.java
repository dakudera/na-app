package tech.na_app.entity.transport;

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
public class TechnicalCertificate {

    private String num_and_series;

    private String issued_by;

    @Temporal(value = TemporalType.DATE)
    private Date date_end;

    @Temporal(value = TemporalType.DATE)
    private Date date_issue;

    private TechnicalCertificateDopInfo technical_certificate_dop_info;

}
