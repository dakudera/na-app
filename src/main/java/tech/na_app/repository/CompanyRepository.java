package tech.na_app.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import tech.na_app.entity.company.Company;

@Repository
public interface CompanyRepository extends MongoRepository<Company, ObjectId> {



}
