package tech.na_app.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import tech.na_app.entity.profile.Education;

import java.util.List;

@Repository
public interface EducationRepository extends MongoRepository<Education, Integer> {

    List<Education> findAllByUserId(Integer userId);


}
