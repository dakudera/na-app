package tech.na_app.services.user_profile;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import tech.na_app.entity.profile.*;
import tech.na_app.entity.user.User;
import tech.na_app.model.ApiException;
import tech.na_app.model.ErrorObject;
import tech.na_app.model.enums.InternshipAndInstructionType;
import tech.na_app.model.profile.*;
import tech.na_app.model.profile.education.*;
import tech.na_app.repository.EducationRepository;
import tech.na_app.repository.InternshipAndInstructionRepository;
import tech.na_app.repository.UserRepository;
import tech.na_app.services.user.UserHelperComponent;
import tech.na_app.utils.ParameterValidator;
import tech.na_app.utils.SequenceGeneratorService;

import java.util.Date;
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
    private final UserHelperComponent userHelperComponent;

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

    public EditInfoEducationResponse editInfoEducation(String requestId, User user, EditInfoEducationRequest request) {
        try {
            if (request.getCertificate() == null || request.getCertificate().isEmpty()) {
                throw new ApiException(400, "BAD REQUEST");
            }
            if (request.getSpecialty() == null || request.getSpecialty().isEmpty()) {
                throw new ApiException(400, "BAD REQUEST");
            }
            if (request.getAdvanced_qualification() == null || request.getAdvanced_qualification().isEmpty()) {
                throw new ApiException(400, "BAD REQUEST");
            }

            User userInfo;
            if (request.getUserId() != null) {
                Optional<User> userOptional = userRepository.findById(request.getUserId());
                userInfo = userOptional.orElse(user);
            } else {
                userInfo = user;
            }

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

    public RemoveInfoEducationResponse removeInfoEducation(String requestId, User user, RemoveInfoEducationRequest request) {
        try {
            User userInfo;
            if (request.getUserId() != null) {
                Optional<User> userOptional = userRepository.findById(request.getUserId());
                userInfo = userOptional.orElse(user);
            } else {
                userInfo = user;
            }

            Optional<Education> educationOptional = educationRepository.findByIdAndUserId(request.getId(), userInfo.getId());
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

    public SaveUserProfileResponse saveUserProfile(String requestId, SaveUserProfileRequest request) {
        try {
            if (request.getId() == null) {
                throw new ApiException(400, "BAD_REQUEST");
            }

            Optional<User> userOptional = userRepository.findById(request.getId());
            if (userOptional.isEmpty()) {
                log.info(requestId + " User was not found");
                throw new ApiException(400, "BAD_REQUEST");
            }

            User user = userOptional.get();
            user.setUpdate_date(new Date());

            String email = request.getEmail();
            if (!ParameterValidator.emailIsValid(request.getEmail())) {
                throw new ApiException(500, "Email is invalid");
            }

            Profile profile = Profile.builder()
                    .email(email)
                    .phone(request.getPhone())
                    .fio(request.getFio())
                    .acc_order_number(request.getAcc_order_number())
                    .acc_order_date(request.getAcc_order_date())
                    .salary(request.getSalary())
                    .birthday(request.getBirthday())
                    .age(userHelperComponent.calculateAge(request.getBirthday()))
                    .previous_work_exp(request.getPrevious_work_exp())
                    .previous_info_work_mp(request.getPrevious_info_work_mp())
                    .sufficient_experience_mp(request.getSufficient_experience_mp())
                    .registration_address(request.getRegistration_address())
                    .actual_address(request.getActual_address())
                    .build();
            user.setProfile(profile);
            userRepository.save(user);

            return new SaveUserProfileResponse(new ErrorObject(0));
        } catch (ApiException e) {
            log.error(requestId + " Error: " + e.getCode() + " Message: " + e.getMessage());
            return new SaveUserProfileResponse(new ErrorObject(e.getCode(), e.getMessage()));
        } catch (Exception e) {
            log.error(requestId + " Message: " + e.getMessage());
            return new SaveUserProfileResponse(new ErrorObject(500, "Something went wrong"));
        }
    }

}
