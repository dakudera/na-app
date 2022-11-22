package tech.na_app.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import tech.na_app.entity.user.UserRolesStore;

@Repository
public interface UserRolesStoreRepository extends MongoRepository<UserRolesStore, Integer> {
}
