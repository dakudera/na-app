package tech.na_app.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import tech.na_app.entity.role_buttons.RoleButtons;
import tech.na_app.model.enums.UserRoleType;

import java.util.Optional;

@Repository
public interface RoleButtonsRepository extends MongoRepository<RoleButtons, String> {

    Optional<RoleButtons> findByRole(UserRoleType role);

}
