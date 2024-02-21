package tech.na_app.services.user_profile.get_data;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import tech.na_app.entity.profile.AvailableDocuments;
import tech.na_app.entity.profile.DrivingLicense;
import tech.na_app.entity.profile.Education;
import tech.na_app.entity.profile.InternshipAndInstruction;
import tech.na_app.entity.user.User;
import tech.na_app.model.ApiException;
import tech.na_app.model.ErrorObject;
import tech.na_app.model.enums.InternshipAndInstructionType;
import tech.na_app.model.profile.GetUserProfileRequest;
import tech.na_app.model.profile.GetUserProfileResponse;
import tech.na_app.repository.*;
import tech.na_app.services.user.UserHelperComponent;
import tech.na_app.services.user_profile.GetUserProfileHelperComponent;
import tech.na_app.services.user_profile.UserProfileAbs;
import tech.na_app.utils.SequenceGeneratorService;

import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class GetUserProfileServiceImpl extends UserProfileAbs implements GetUserProfileService {

    private final EducationRepository educationRepository;
    private final InternshipAndInstructionRepository internshipAndInstructionRepository;
    private final GetUserProfileHelperComponent getUserProfileHelperComponent;
    private final DrivingLicenseRepository drivingLicenseRepository;
    private final AvailableDocumentsRepository availableDocumentsRepository;

    @Override
    public GetUserProfileResponse getUserProfile(String requestId, User user, GetUserProfileRequest request) {
        try {
            User userInfo = choosingUser(user, request.getUserId());

            DrivingLicense drivingLicense = drivingLicenseRepository.findByUserId(userInfo.getId())
                    .orElseGet(DrivingLicense::new);
            List<Education> educations = educationRepository.findAllByUserId(userInfo.getId());
            List<InternshipAndInstruction> internships = internshipAndInstructionRepository
                    .findAllByUserIdAndType(userInfo.getId(), InternshipAndInstructionType.INTERNSHIP);
            List<InternshipAndInstruction> instructions = internshipAndInstructionRepository
                    .findAllByUserIdAndType(userInfo.getId(), InternshipAndInstructionType.INSTRUCTION);
            AvailableDocuments availableDocuments = availableDocumentsRepository.findByUserId(userInfo.getId())
                    .orElseGet(AvailableDocuments::new);
            GetUserProfileResponse response = new GetUserProfileResponse(new ErrorObject(0));
            response.setId(userInfo.getId());
            response.setDriving_license(getUserProfileHelperComponent.fillDriverLicense(drivingLicense));
            response.setEducationInfo(getUserProfileHelperComponent.buildEducations(educations));
            response.setInternshipInfo(getUserProfileHelperComponent.buildInstructionsAndInternships(internships));
            response.setInstructionInfo(getUserProfileHelperComponent.buildInstructionsAndInternships(instructions));
            response.setAvailable_documents(getUserProfileHelperComponent.fillDriverLicense(availableDocuments));

            if (userInfo.getProfile() != null) {
                getUserProfileHelperComponent.fillUserProfile(userInfo, response);
            }

            return response;
        } catch (ApiException e) {
            log.error(requestId + " Error: " + e.getCode() + " Message: " + e.getMessage());
            return new GetUserProfileResponse(new ErrorObject(e.getCode(), e.getMessage()));
        } catch (Exception e) {
            log.error(requestId + " Message: " + e.getMessage());
            return new GetUserProfileResponse(new ErrorObject(500, "Something went wrong"));
        }
    }
}
