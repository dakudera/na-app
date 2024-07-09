package tech.na_app.services.user_profile.remove_data;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import tech.na_app.entity.profile.InternshipAndInstruction;
import tech.na_app.entity.user.User;
import tech.na_app.model.exceptions.ApiException;
import tech.na_app.model.exceptions.ErrorObject;
import tech.na_app.model.profile.RemoveInternshipRequest;
import tech.na_app.model.profile.SaveInternshipResponse;
import tech.na_app.repository.InternshipAndInstructionRepository;
import tech.na_app.services.user_profile.UserProfileAbs;

import java.util.Optional;

@Log4j2
@Service
@RequiredArgsConstructor
public class RemoveInternshipServiceImpl extends UserProfileAbs implements RemoveInternshipService {

    private final InternshipAndInstructionRepository internshipAndInstructionRepository;

    @Override
    public SaveInternshipResponse removeInternship(String requestId, User user, RemoveInternshipRequest request) {
        try {
            User userInfo = choosingUser(user, request.getUserId());

            Optional<InternshipAndInstruction> internshipAndInstructionOptional = internshipAndInstructionRepository.findByUserIdAndId(userInfo.getId(), request.getId());
            InternshipAndInstruction internshipAndInstruction = internshipAndInstructionOptional.orElseThrow(() -> new ApiException(400, "BAD REQUEST"));
            internshipAndInstructionRepository.delete(internshipAndInstruction);

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
