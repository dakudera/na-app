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
import tech.na_app.model.profile.driving_license.*;
import tech.na_app.model.profile.education.*;
import tech.na_app.model.user.SelectedUser;
import tech.na_app.repository.*;
import tech.na_app.services.user.UserHelperComponent;
import tech.na_app.utils.ParameterValidator;
import tech.na_app.utils.SequenceGeneratorService;

import java.util.Date;
import java.util.List;
import java.util.Objects;
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
    private final DrivingLicenseRepository drivingLicenseRepository;
    private final AvailableDocumentsRepository availableDocumentsRepository;

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

    public SaveInfoDrivingLicenseResponse saveInfoDrivingLicense(String requestId, SaveInfoDrivingLicenseRequest request) {
        try {
            if (Objects.isNull(request)) {
                throw new ApiException(400, "BAD REQUEST");
            }
            if (Objects.isNull(request.getUserId())) {
                throw new ApiException(400, "BAD REQUEST");
            }
            if (Objects.isNull(request.getCategories()) || request.getCategories().isEmpty()) {
                throw new ApiException(400, "BAD REQUEST");
            }
            if (Objects.isNull(request.getDate_end())) {
                throw new ApiException(400, "BAD REQUEST");
            }
            if (Objects.isNull(request.getDate_issue())) {
                throw new ApiException(400, "BAD REQUEST");
            }
            if (drivingLicenseRepository.findByUserId(request.getUserId()).isPresent()) {
                throw new ApiException(400, "User already has driving license");
            }

            DrivingLicenseSequence sequenceNumber = (DrivingLicenseSequence) sequenceGeneratorService.getSequenceNumber(DrivingLicense.SEQUENCE_NAME, DrivingLicenseSequence.class);

            drivingLicenseRepository.save(DrivingLicense.builder()
                    .id(sequenceNumber.getSeq())
                    .userId(request.getUserId())
                    .categories(request.getCategories())
                    .date_issue(request.getDate_issue())
                    .date_end(request.getDate_end())
                    .build());

            return new SaveInfoDrivingLicenseResponse(new ErrorObject(0));
        } catch (ApiException e) {
            log.error(requestId + " Error: " + e.getCode() + " Message: " + e.getMessage());
            return new SaveInfoDrivingLicenseResponse(new ErrorObject(e.getCode(), e.getMessage()));
        } catch (Exception e) {
            log.error(requestId + " Message: " + e.getMessage());
            return new SaveInfoDrivingLicenseResponse(new ErrorObject(500, "Something went wrong"));
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

            User userInfo = choosingUser(user, request.getUserId()).getUser();

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

    public EditInfoDrivingLicenseResponse editInfoDrivingLicense(String requestId, User user, EditInfoDrivingLicenseRequest request) {
        try {
            if (Objects.isNull(request)) {
                throw new ApiException(400, "BAD REQUEST");
            }
            if (Objects.isNull(request.getCategories()) || request.getCategories().isEmpty()) {
                throw new ApiException(400, "BAD REQUEST");
            }
            if (Objects.isNull(request.getDate_end())) {
                throw new ApiException(400, "BAD REQUEST");
            }
            if (Objects.isNull(request.getDate_issue())) {
                throw new ApiException(400, "BAD REQUEST");
            }

            User userInfo = choosingUser(user, request.getUserId()).getUser();

            DrivingLicense drivingLicense = drivingLicenseRepository.findByUserId(userInfo.getId())
                    .orElseThrow(() -> new ApiException(400, "BAD REQUEST"));
            drivingLicense.setCategories(request.getCategories());
            drivingLicense.setDate_issue(request.getDate_issue());
            drivingLicense.setDate_end(request.getDate_end());
            drivingLicenseRepository.save(drivingLicense);

            return new EditInfoDrivingLicenseResponse(new ErrorObject(0));
        } catch (ApiException e) {
            log.error(requestId + " Error: " + e.getCode() + " Message: " + e.getMessage());
            return new EditInfoDrivingLicenseResponse(new ErrorObject(e.getCode(), e.getMessage()));
        } catch (Exception e) {
            log.error(requestId + " Message: " + e.getMessage());
            return new EditInfoDrivingLicenseResponse(new ErrorObject(500, "Something went wrong"));
        }
    }

    public RemoveInfoEducationResponse removeInfoEducation(String requestId, User user, RemoveInfoEducationRequest request) {
        try {
            User userInfo = choosingUser(user, request.getUserId()).getUser();

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

    public RemoveInfoDrivingLicenseResponse removeInfoDrivingLicense(String requestId, User user, RemoveInfoDrivingLicenseRequest request) {
        try {
            if (Objects.isNull(request.getUserId()) || Objects.isNull(request.getId())) {
                throw new ApiException(400, "BAD REQUEST");
            }
            User userInfo = choosingUser(user, request.getUserId()).getUser();

            DrivingLicense drivingLicense = drivingLicenseRepository.findByIdAndUserId(request.getId(), userInfo.getId())
                    .orElseThrow(() -> new ApiException(400, "BAD REQUEST"));
            drivingLicenseRepository.delete(drivingLicense);
            return new RemoveInfoDrivingLicenseResponse(new ErrorObject(0));
        } catch (ApiException e) {
            log.error(requestId + " Error: " + e.getCode() + " Message: " + e.getMessage());
            return new RemoveInfoDrivingLicenseResponse(new ErrorObject(e.getCode(), e.getMessage()));
        } catch (Exception e) {
            log.error(requestId + " Message: " + e.getMessage());
            return new RemoveInfoDrivingLicenseResponse(new ErrorObject(500, "Something went wrong"));
        }
    }

    public SaveInternshipResponse saveInternship(String requestId, User user, SaveInternshipRequest request) {
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

            User userInfo = choosingUser(user, request.getUserId()).getUser();

            if (request.getId() == null) {
                InternshipAndInstructionSequence sequenceNumber = (InternshipAndInstructionSequence) sequenceGeneratorService
                        .getSequenceNumber(InternshipAndInstruction.SEQUENCE_NAME, InternshipAndInstructionSequence.class);
                internshipAndInstructionRepository.save(
                        InternshipAndInstruction.builder()
                                .id(sequenceNumber.getSeq())
                                .userId(userInfo.getId())
                                .type(request.getType())
                                .date(request.getDate())
                                .doc_number(request.getDoc_number())
                                .build()
                );
            } else {
                internshipAndInstructionRepository.save(
                        InternshipAndInstruction.builder()
                                .id(request.getId())
                                .userId(userInfo.getId())
                                .type(request.getType())
                                .date(request.getDate())
                                .doc_number(request.getDoc_number())
                                .build()
                );
            }

            return new SaveInternshipResponse(new ErrorObject(0));
        } catch (ApiException e) {
            log.error(requestId + " Error: " + e.getCode() + " Message: " + e.getMessage());
            return new SaveInternshipResponse(new ErrorObject(e.getCode(), e.getMessage()));
        } catch (Exception e) {
            log.error(requestId + " Message: " + e.getMessage());
            return new SaveInternshipResponse(new ErrorObject(500, "Something went wrong"));
        }
    }

    public SaveInternshipResponse removeInternship(String requestId, User user, RemoveInternshipRequest request) {
        try {
            User userInfo = choosingUser(user, request.getUserId()).getUser();

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

    public GetUserProfileResponse getUserProfile(String requestId, User user, GetUserProfileRequest request) {
        try {
            User userInfo = choosingUser(user, request.getUserId()).getUser();

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
            response.setDriving_license(drivingLicense);
            response.setEducationInfo(getUserProfileHelperComponent.buildEducations(educations));
            response.setInternshipInfo(getUserProfileHelperComponent.buildInstructionsAndInternships(internships));
            response.setInstructionInfo(getUserProfileHelperComponent.buildInstructionsAndInternships(instructions));
            response.setAvailable_documents(availableDocuments);

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

    public ExistDocumentResponse saveExistDocument(String requestId, User user, ExistDocumentRequest request) {
        try {
            if (request == null) {
                throw new ApiException(400, "BAD REQUEST");
            }

            User userInfo = choosingUser(user, request.getUserId()).getUser();

            Optional<AvailableDocuments> availableDocumentsOptional = availableDocumentsRepository.findByUserId(userInfo.getId());
            AvailableDocuments availableDocuments;
            // do update
            if (availableDocumentsOptional.isPresent()) {
                availableDocuments = availableDocumentsOptional.get();

                availableDocuments.setIpn(request.getIpn());
                availableDocuments.setPassport(request.getPassport());
                availableDocuments.setEmployment_history(request.getEmployment_history());
                availableDocuments.setHealth_certificate(request.getHealth_certificate());
                availableDocuments.setMilitary_registration_doc(request.getMilitary_registration_doc());
            } else { // do save new entry
                AvailableDocumentsSequence sequenceNumber = (AvailableDocumentsSequence) sequenceGeneratorService.getSequenceNumber(AvailableDocuments.SEQUENCE_NAME, AvailableDocumentsSequence.class);

                availableDocuments = AvailableDocuments.builder()
                        .id(sequenceNumber.getSeq())
                        .ipn(request.getIpn())
                        .passport(request.getPassport())
                        .employment_history(request.getEmployment_history())
                        .health_certificate(request.getHealth_certificate())
                        .military_registration_doc(request.getMilitary_registration_doc())
                        .userId(userInfo.getId())
                        .build();
            }
            availableDocumentsRepository.save(availableDocuments);

            return new ExistDocumentResponse(new ErrorObject(0));
        } catch (ApiException e) {
            log.error(requestId + " Error: " + e.getCode() + " Message: " + e.getMessage());
            return new ExistDocumentResponse(new ErrorObject(e.getCode(), e.getMessage()));
        } catch (Exception e) {
            log.error(requestId + " Message: " + e.getMessage());
            return new ExistDocumentResponse(new ErrorObject(500, "Something went wrong"));
        }

    }

    public EditUserProfileResponse editUserProfile(String requestId, User userThatMakeRequest, EditUserProfileRequest request) {
        try {
            if (Objects.isNull(request)) {
                throw new ApiException(400, "BAD REQUEST");
            }
            if (Objects.isNull(request.getRole())) {
                throw new ApiException(400, "BAD REQUEST");
            }

            String email = request.getEmail();
            if (!ParameterValidator.emailIsValid(request.getEmail())) {
                throw new ApiException(500, "Email is invalid");
            }

            SelectedUser selectedUser = choosingUser(userThatMakeRequest, request.getId());

            if (userThatMakeRequest.getRole().getValue() >= selectedUser.getUser().getRole().getValue() && !selectedUser.getIsSelfUser() ||
                    userThatMakeRequest.getRole().getValue() > request.getRole().getValue()) {
                throw new ApiException(500, "denied");
            }

            User userInfo = selectedUser.getUser();
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
            userInfo.setRole(request.getRole());
            userInfo.setProfile(profile);
            userInfo.setUpdate_date(new Date());
            userRepository.save(userInfo);

            return new EditUserProfileResponse(new ErrorObject(0));
        } catch (ApiException e) {
            log.error(requestId + " Error: " + e.getCode() + " Message: " + e.getMessage());
            return new EditUserProfileResponse(new ErrorObject(e.getCode(), e.getMessage()));
        } catch (Exception e) {
            log.error(requestId + " Message: " + e.getMessage());
            return new EditUserProfileResponse(new ErrorObject(500, "Something went wrong"));
        }
    }

    public SelectedUser choosingUser(User user, Integer userId) {
        if (Objects.nonNull(userId)) {
            User otherUser = userRepository.findById(userId).orElseThrow(() -> new ApiException(400, "User was not found"));
            log.info("Other user {}", otherUser);
            return SelectedUser.builder()
                    .user(otherUser)
                    .isSelfUser(false)
                    .build();
        } else {
            log.info("Self user {}", user);
            return SelectedUser.builder()
                    .user(user)
                    .isSelfUser(true)
                    .build();
        }
    }

}
