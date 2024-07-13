package tech.na_app.entity.role_buttons;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;
import tech.na_app.model.enums.UserRoleType;

import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "role_buttons")
public class RoleButtons {

    @MongoId
    private ObjectId id;

    private UserRoleType role;

    private List<Button> allowed_buttons;

}
