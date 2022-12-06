package tech.na_app.entity.role_buttons;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import tech.na_app.model.enums.Buttons;
import tech.na_app.model.enums.UserRoleType;

import javax.persistence.Id;
import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "role_buttons")
public class RoleButtons {

    @Id
    private String id;

    private UserRoleType role;

    private List<Buttons> allowed_buttons;

}
