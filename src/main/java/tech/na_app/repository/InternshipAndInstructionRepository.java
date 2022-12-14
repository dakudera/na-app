package tech.na_app.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import tech.na_app.entity.profile.InternshipAndInstruction;
import tech.na_app.model.enums.InternshipAndInstructionType;

import java.util.List;

@Repository
public interface InternshipAndInstructionRepository extends MongoRepository<InternshipAndInstruction, Integer> {
    List<InternshipAndInstruction> findAllByUserIdAndType(Integer userId, InternshipAndInstructionType type);

}
