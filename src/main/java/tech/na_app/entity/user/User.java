package tech.na_app.entity.user;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.mongodb.core.mapping.Document;
import tech.na_app.entity.profile.Profile;
import tech.na_app.model.enums.UserRoleType;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "users")
public class User implements Serializable {

    @Transient
    public static final String SEQUENCE_NAME = "users_sequence";

    @Id
    private Integer id;

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

    private Integer companyId;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", role=" + role +
                '}';
    }

}
