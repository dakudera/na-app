package tech.na_app.model.user;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.na_app.entity.user.User;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SelectedUser {

    private User user;

    private Boolean isSelfUser;
}
