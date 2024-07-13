package tech.na_app.services.user_profile.remove_data;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import tech.na_app.entity.profile.Education;
import tech.na_app.entity.user.User;
import tech.na_app.model.exceptions.ApiException;
import tech.na_app.model.exceptions.ErrorObject;
import tech.na_app.model.profile.education.RemoveInfoEducationRequest;
import tech.na_app.model.profile.education.RemoveInfoEducationResponse;
import tech.na_app.repository.EducationRepository;
import tech.na_app.services.user_profile.UserProfileAbs;

import java.util.Optional;

@Log4j2
@Service
@RequiredArgsConstructor
public class RemoveInfoEducationServiceImpl extends UserProfileAbs implements RemoveInfoEducationService {

    private final EducationRepository educationRepository;

    @Override
    public RemoveInfoEducationResponse removeInfoEducation(
            String requestId, User user, RemoveInfoEducationRequest request
    ) {
        try {
            User userInfo = choosingUser(user, request.userId());

            Optional<Education> educationOptional = educationRepository
                    .findByIdAndUserId(new ObjectId(request.id()), userInfo.getId());
            Education education = educationOptional.orElseThrow(() -> new ApiException(400, "BAD REQUEST"));
            educationRepository.delete(education);

            return new RemoveInfoEducationResponse(new ErrorObject(0));
        } catch (ApiException e) {
            log.error(requestId + " Error: " + e.getCode() + " Message: " + e.getMessage());
            return new RemoveInfoEducationResponse(new ErrorObject(e.getCode(), e.getMessage()));
        } catch (Exception e) {
            log.error(requestId + " Message: " + e.getMessage());
            return new RemoveInfoEducationResponse(new ErrorObject(500, "Something went wrong"));
        }
    }
}
