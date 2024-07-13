package tech.na_app.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import tech.na_app.entity.transport.Transport;

import java.util.List;
import java.util.Optional;

public interface TransportRepository extends MongoRepository<Transport, ObjectId> {

    List<Transport> findAllByCompanyId(ObjectId companyId);

    Optional<Transport> findByIdAndCompanyId(Integer id, ObjectId companyId);

}
