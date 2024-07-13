package tech.na_app.entity.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;
import tech.na_app.model.enums.UserRoleType;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "user_roles")
public class UserRolesStore {

    @MongoId
    private ObjectId id;

    private UserRoleType role;
    private String description;

}
