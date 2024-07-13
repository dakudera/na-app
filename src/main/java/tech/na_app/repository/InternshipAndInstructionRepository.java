package tech.na_app.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import tech.na_app.entity.profile.InternshipAndInstruction;
import tech.na_app.model.enums.InternshipAndInstructionType;

import java.util.List;
import java.util.Optional;

@Repository
public interface InternshipAndInstructionRepository extends MongoRepository<InternshipAndInstruction, ObjectId> {

    List<InternshipAndInstruction> findAllByUserIdAndType(ObjectId userId, InternshipAndInstructionType type);

    Optional<InternshipAndInstruction> findByUserIdAndId(ObjectId userId, ObjectId id);

}
