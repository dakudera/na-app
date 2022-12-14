package tech.na_app.services.user_profile;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import tech.na_app.entity.profile.Education;
import tech.na_app.entity.profile.EducationSequence;
import tech.na_app.entity.profile.InternshipAndInstruction;
import tech.na_app.entity.profile.InternshipAndInstructionSequence;
import tech.na_app.entity.user.User;
import tech.na_app.model.ApiException;
import tech.na_app.model.ErrorObject;
import tech.na_app.model.enums.InternshipAndInstructionType;
import tech.na_app.model.profile.*;
import tech.na_app.repository.EducationRepository;
import tech.na_app.repository.InternshipAndInstructionRepository;
import tech.na_app.repository.UserRepository;
import tech.na_app.utils.SequenceGeneratorService;

import java.util.List;
import java.util.Optional;

@Log4j2
@Service
@RequiredArgsConstructor
public class UserProfileService {

    private final EducationRepository educationRepository;
    private final SequenceGeneratorService sequenceGeneratorService;
    private final InternshipAndInstructionRepository internshipAndInstructionRepository;
    private final UserRepository userRepository;
    private final GetUserProfileHelperComponent getUserProfileHelperComponent;

    public SaveInfoEducationResponse saveInfoEducation(String requestId, SaveInfoEducationRequest request) {
        try {
            if (request.getUserId() == null) {
                throw new ApiException(400, "BAD REQUEST");
            }
            if (request.getCertificate() == null || request.getCertificate().isEmpty()) {
                throw new ApiException(400, "BAD REQUEST");
            }
            if (request.getSpecialty() == null || request.getSpecialty().isEmpty()) {
                throw new ApiException(400, "BAD REQUEST");
            }
            if (request.getAdvanced_qualification() == null || request.getAdvanced_qualification().isEmpty()) {
                throw new ApiException(400, "BAD REQUEST");
            }

            EducationSequence sequenceNumber = (EducationSequence) sequenceGeneratorService.getSequenceNumber(Education.SEQUENCE_NAME, EducationSequence.class);

            educationRepository.save(
                    Education.builder()
                            .id(sequenceNumber.getSeq())
                            .userId(request.getUserId())
                            .certificate(request.getCertificate())
                            .specialty(request.getSpecialty())
                            .advanced_qualification(request.getAdvanced_qualification())
                            .build()
            );

            return new SaveInfoEducationResponse(new ErrorObject(0));
        } catch (ApiException e) {
            log.error(requestId + " Error: " + e.getCode() + " Message: " + e.getMessage());
            return new SaveInfoEducationResponse(new ErrorObject(e.getCode(), e.getMessage()));
        } catch (Exception e) {
            log.error(requestId + " Message: " + e.getMessage());
            return new SaveInfoEducationResponse(new ErrorObject(500, "Something went wrong"));
        }
    }

    public SaveInternshipResponse saveInternship(String requestId, SaveInternshipRequest request) {
        try {
            if (request.getUserId() == null) {
                throw new ApiException(400, "BAD REQUEST");
            }
            if (request.getDate() == null) {
                throw new ApiException(400, "BAD REQUEST");
            }
            if (request.getDoc_number() == null || request.getDoc_number().isEmpty()) {
                throw new ApiException(400, "BAD REQUEST");
            }


            InternshipAndInstructionSequence sequenceNumber = (InternshipAndInstructionSequence) sequenceGeneratorService
                    .getSequenceNumber(InternshipAndInstruction.SEQUENCE_NAME, InternshipAndInstructionSequence.class);
            internshipAndInstructionRepository.save(
                    InternshipAndInstruction.builder()
                            .id(sequenceNumber.getSeq())
                            .userId(request.getUserId())
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

    public GetUserProfileResponse getUserProfile(String requestId, User user, GetUserProfileRequest request) {
        try {
            User userInfo;
            if (request != null && request.getUserId() != null) {
                Optional<User> userOptional = userRepository.findById(request.getUserId());
                userInfo = userOptional.orElse(user);
            } else {
                userInfo = user;
            }

            List<Education> educations = educationRepository.findAllByUserId(userInfo.getId());
            List<InternshipAndInstruction> internships = internshipAndInstructionRepository
                    .findAllByUserIdAndType(userInfo.getId(), InternshipAndInstructionType.INTERNSHIP);
            List<InternshipAndInstruction> instructions = internshipAndInstructionRepository
                    .findAllByUserIdAndType(userInfo.getId(), InternshipAndInstructionType.INSTRUCTION);
            GetUserProfileResponse response = new GetUserProfileResponse(new ErrorObject(0));
            response.setId(userInfo.getId());
            response.setEducationInfo(getUserProfileHelperComponent.buildEducations(educations));
            response.setInternshipInfo(getUserProfileHelperComponent.buildInstructionsAndInternships(internships));
            response.setInstructionInfo(getUserProfileHelperComponent.buildInstructionsAndInternships(instructions));

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
