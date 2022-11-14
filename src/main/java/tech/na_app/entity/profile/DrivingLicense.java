package tech.na_app.entity.profile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.na_app.model.enums.DriverLicenceCategory;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DrivingLicense {

    private List<DriverLicenceCategory> categories;

    @Temporal(TemporalType.DATE)
    private Date date_issue;

    @Temporal(TemporalType.DATE)
    private Date date_end;

}
