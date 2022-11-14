package tech.na_app.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import tech.na_app.entity.user.User;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, Integer> {

    Optional<User> findByLoginAndPassword(String login, String password);

}
