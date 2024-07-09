package tech.na_app.services.user_profile.save_data;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import tech.na_app.entity.profile.InternshipAndInstruction;
import tech.na_app.entity.profile.InternshipAndInstructionSequence;
import tech.na_app.entity.user.User;
import tech.na_app.model.exceptions.ApiException;
import tech.na_app.model.exceptions.ErrorObject;
import tech.na_app.model.profile.SaveInternshipRequest;
import tech.na_app.model.profile.SaveInternshipResponse;
import tech.na_app.repository.InternshipAndInstructionRepository;
import tech.na_app.services.user_profile.UserProfileAbs;
import tech.na_app.utils.SequenceGeneratorService;

@Log4j2
@Service
@RequiredArgsConstructor
public class SaveInternshipServiceImpl extends UserProfileAbs implements SaveInternshipService {

    private final SequenceGeneratorService sequenceGeneratorService;
    private final InternshipAndInstructionRepository internshipAndInstructionRepository;

    @Override
    public SaveInternshipResponse saveInternship(String requestId, User user, SaveInternshipRequest request) {
        try {
            User userInfo = choosingUser(user, request.getUserId());

            Integer id;
            if (request.getId() == null) {
                InternshipAndInstructionSequence sequenceNumber = (InternshipAndInstructionSequence) sequenceGeneratorService
                        .getSequenceNumber(InternshipAndInstruction.SEQUENCE_NAME, InternshipAndInstructionSequence.class);
                id = sequenceNumber.getSeq();
            } else {
                id = request.getId();
            }
            internshipAndInstructionRepository.save(
                    InternshipAndInstruction.builder()
                            .id(id)
                            .userId(userInfo.getId())
                            .type(request.getType())
                            .date(request.getDate())
                            .doc_number(request.getDoc_number())
                            .build()
            );

            return new SaveInternshipResponse(new ErrorObject(0));
        } catch (ApiException e) {
            log.error(requestId + " Error: " + e.getCode() + " Message: " + e.getMessage());
            return new SaveInternshipResponse(new ErrorObject(e.getCode(), e.getMessage()));
        } catch (Exception e) {
            log.error(requestId + " Message: " + e.getMessage());
            return new SaveInternshipResponse(new ErrorObject(500, "Something went wrong"));
        }
    }
}
