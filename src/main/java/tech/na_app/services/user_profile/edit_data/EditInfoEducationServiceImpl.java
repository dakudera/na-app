package tech.na_app.services.user_profile.edit_data;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import tech.na_app.entity.profile.Education;
import tech.na_app.entity.user.User;
import tech.na_app.model.ApiException;
import tech.na_app.model.ErrorObject;
import tech.na_app.model.profile.education.EditInfoEducationRequest;
import tech.na_app.model.profile.education.EditInfoEducationResponse;
import tech.na_app.repository.EducationRepository;
import tech.na_app.services.user_profile.UserProfileAbs;

import java.util.Optional;

@Log4j2
@Service
@RequiredArgsConstructor
public class EditInfoEducationServiceImpl extends UserProfileAbs implements EditInfoEducationService {

    private final EducationRepository educationRepository;

    @Override
    public EditInfoEducationResponse editInfoEducation(String requestId, User user, EditInfoEducationRequest request) {
        try {
            User userInfo = choosingUser(user, request.getUserId());

            Optional<Education> educationOptional = educationRepository.findByIdAndUserId(request.getId(), userInfo.getId());
            Education education = educationOptional.orElseThrow(() -> new ApiException(400, "BAD REQUEST"));
            education.setCertificate(request.getCertificate());
            education.setSpecialty(request.getSpecialty());
            education.setAdvanced_qualification(request.getAdvanced_qualification());
            educationRepository.save(education);

            return new EditInfoEducationResponse(new ErrorObject(0));
        } catch (ApiException e) {
            log.error(requestId + " Error: " + e.getCode() + " Message: " + e.getMessage());
            return new EditInfoEducationResponse(new ErrorObject(e.getCode(), e.getMessage()));
        } catch (Exception e) {
            log.error(requestId + " Message: " + e.getMessage());
            return new EditInfoEducationResponse(new ErrorObject(500, "Something went wrong"));
        }
    }
}
