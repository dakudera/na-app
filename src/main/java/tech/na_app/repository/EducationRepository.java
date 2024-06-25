package tech.na_app.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import tech.na_app.entity.profile.Education;

import java.util.List;
import java.util.Optional;

@Repository
public interface EducationRepository extends MongoRepository<Education, Integer> {

    List<Education> findAllByUserId(ObjectId userId);

    Optional<Education> findByIdAndUserId(Integer id, ObjectId userId);

}
