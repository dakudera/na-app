package tech.na_app.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import tech.na_app.entity.profile.DrivingLicense;

import java.util.Optional;

@Repository
public interface DrivingLicenseRepository extends MongoRepository<DrivingLicense, String> {

    Optional<DrivingLicense> findByUserId(ObjectId userId);

}
