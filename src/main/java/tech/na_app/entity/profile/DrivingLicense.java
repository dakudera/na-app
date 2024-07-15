package tech.na_app.entity.profile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;
import tech.na_app.model.enums.DriverLicenceCategory;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document("driving_license")
public class DrivingLicense {

    @MongoId
    private ObjectId id;

    private Set<DriverLicenceCategory> categories;

    @Temporal(TemporalType.DATE)
    private Date date_issue;

    @Temporal(TemporalType.DATE)
    private Date date_end;

    private ObjectId userId;

}
