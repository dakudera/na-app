package tech.na_app.entity.user;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;
import tech.na_app.entity.profile.Profile;
import tech.na_app.model.enums.UserRoleType;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "users")
public class User implements Serializable {


    @MongoId
    private ObjectId id;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date create_date;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date update_date;

    private String login;
    private String password;
    private String salt;

    @Enumerated(EnumType.STRING)
    private UserRoleType role;

    private Profile profile;

    private ObjectId companyId;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", role=" + role +
                '}';
    }

}
