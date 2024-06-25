package tech.na_app.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import tech.na_app.entity.profile.AvailableDocuments;

import java.util.Optional;

@Repository
public interface AvailableDocumentsRepository extends MongoRepository<AvailableDocuments, String> {

    Optional<AvailableDocuments> findByUserId(ObjectId userId);

}
