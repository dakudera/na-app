package tech.na_app.converter;

import tech.na_app.entity.profile.*;
import tech.na_app.entity.user.User;
import tech.na_app.model.exceptions.ErrorObject;
import tech.na_app.model.profile.GetUserProfileResponse;
import tech.na_app.model.profile.InstructionInfo;
import tech.na_app.model.profile.education.EducationInfo;

import java.util.ArrayList;
import java.util.List;


public class GetUserProfileHelper {

    private final List<Education> educations;
    private final List<InternshipAndInstruction> instructions;
    private final List<InternshipAndInstruction> internships;
    private final User userInfo;
    private final DrivingLicense drivingLicense;
    private final AvailableDocuments availableDocuments;

    public GetUserProfileHelper(
            List<Education> educations, List<InternshipAndInstruction> instructions,
            User userInfo, DrivingLicense drivingLicense, AvailableDocuments availableDocuments,
            List<InternshipAndInstruction> internships) {
        this.educations = educations;
        this.instructions = instructions;
        this.userInfo = userInfo;
        this.drivingLicense = drivingLicense;
        this.availableDocuments = availableDocuments;
        this.internships = internships;
    }

    public GetUserProfileResponse buildGetUserProfileResponse() {
        GetUserProfileResponse response = new GetUserProfileResponse(new ErrorObject(0));
        response.setId(userInfo.getId().toHexString());
        response.setRole(userInfo.getRole());
        response.setDriving_license(this.fillDriverLicense());
        response.setEducationInfo(this.buildEducations());
        response.setInternshipInfo(this.buildInstructionsAndInternships(internships));
        response.setInstructionInfo(this.buildInstructionsAndInternships(instructions));
        response.setAvailable_documents(this.fillAvailableDocuments());

        if (userInfo.getProfile() != null) {
            this.fillUserProfile();
        }

        return response;
    }

    private List<EducationInfo> buildEducations() {
        List<EducationInfo> educationInfo = new ArrayList<>();
        if (educations == null || educations.isEmpty()) {
            return educationInfo;
        }

        educations.forEach(
                e -> educationInfo.add(
                        EducationInfo.builder()
                                .id(e.getId().toHexString())
                                .advanced_qualification(e.getAdvanced_qualification())
                                .certificate(e.getCertificate())
                                .specialty(e.getSpecialty())
                                .build()
                )
        );
        return educationInfo;
    }

    private List<InstructionInfo> buildInstructionsAndInternships(List<InternshipAndInstruction> list) {
        List<InstructionInfo> instructionInfo = new ArrayList<>();
        if (list == null || list.isEmpty()) {
            return instructionInfo;
        }

        list.forEach(
                e -> instructionInfo.add(
                        InstructionInfo.builder()
                                .id(e.getId())
                                .doc_number(e.getDoc_number())
                                .date(e.getDate())
                                .build()
                )
        );
        return instructionInfo;
    }

    private GetUserProfileResponse fillUserProfile() {
        GetUserProfileResponse response = new GetUserProfileResponse(new ErrorObject(0));
        Profile profile = userInfo.getProfile();
        response.setRole(userInfo.getRole());
        response.setEmail(profile.getEmail());
        response.setPhone(profile.getPhone());
        response.setFio(profile.getFio());
        response.setAcc_order_number(profile.getAcc_order_number());
        response.setAcc_order_date(profile.getAcc_order_date());
        response.setSalary(profile.getSalary());
        response.setBirthday(profile.getBirthday());
        response.setPrevious_work_exp(profile.getPrevious_work_exp());
        response.setPrevious_info_work_mp(profile.getPrevious_info_work_mp());
        response.setSufficient_experience_mp(profile.getSufficient_experience_mp());
        response.setRegistration_address(profile.getRegistration_address());
        response.setActual_address(profile.getActual_address());
        return response;
    }

    private tech.na_app.model.profile.driving_license.DrivingLicense fillDriverLicense() {
        tech.na_app.model.profile.driving_license.DrivingLicense driving = new tech.na_app.model.profile.driving_license.DrivingLicense();
        driving.setCategories(drivingLicense.getCategories());
        driving.setDate_issue(drivingLicense.getDate_issue());
        driving.setDate_end(drivingLicense.getDate_end());
        return driving;
    }


    private tech.na_app.model.profile.AvailableDocuments fillAvailableDocuments() {
        tech.na_app.model.profile.AvailableDocuments documents = new tech.na_app.model.profile.AvailableDocuments();
        documents.setEmployment_history(availableDocuments.getEmployment_history());
        documents.setIpn(availableDocuments.getIpn());
        documents.setPassport(availableDocuments.getPassport());
        documents.setMilitary_registration_doc(availableDocuments.getMilitary_registration_doc());
        documents.setHealth_certificate(availableDocuments.getHealth_certificate());
        return documents;
    }

}
