package tech.na_app.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import tech.na_app.entity.user.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, Integer> {

    Optional<User> findByLoginAndPassword(String login, String password);

    Optional<User> findByLogin(String login);

    List<User> findAllByCompanyId(ObjectId companyId);

    Optional<User> findById(ObjectId integer);
}
