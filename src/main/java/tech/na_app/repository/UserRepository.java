package tech.na_app.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import tech.na_app.entity.user.User;

@Repository
public interface UserRepository extends MongoRepository<User, Integer> {
}
