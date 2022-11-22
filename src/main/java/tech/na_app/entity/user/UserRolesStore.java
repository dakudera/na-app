package tech.na_app.entity.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import tech.na_app.model.enums.UserRoleType;

import javax.persistence.Id;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "user_roles")
public class UserRolesStore {

    @Id
    private Integer id;

    private UserRoleType role;
    private String description;

}
