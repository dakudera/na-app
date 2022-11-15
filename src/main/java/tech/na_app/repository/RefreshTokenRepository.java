package tech.na_app.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import tech.na_app.entity.auth.RefreshToken;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends MongoRepository<RefreshToken, Integer> {

    Optional<RefreshToken> findByToken(String token);

}
