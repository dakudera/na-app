package tech.na_app.services.user_profile;

import org.springframework.stereotype.Component;
import tech.na_app.entity.profile.*;
import tech.na_app.entity.user.User;
import tech.na_app.model.profile.GetUserProfileResponse;
import tech.na_app.model.profile.InstructionInfo;
import tech.na_app.model.profile.education.EducationInfo;

import java.util.ArrayList;
import java.util.List;

@Component
public class GetUserProfileHelperComponent {

    public List<EducationInfo> buildEducations(List<Education> educations) {
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

    public List<InstructionInfo> buildInstructionsAndInternships(List<InternshipAndInstruction> instructions) {
        List<InstructionInfo> instructionInfo = new ArrayList<>();
        if (instructions == null || instructions.isEmpty()) {
            return instructionInfo;
        }

        instructions.forEach(
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

    public void fillUserProfile(User userInfo, GetUserProfileResponse response) {
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
    }

    public tech.na_app.model.profile.driving_license.DrivingLicense fillDriverLicense(DrivingLicense drivingLicense) {
        tech.na_app.model.profile.driving_license.DrivingLicense driving = new tech.na_app.model.profile.driving_license.DrivingLicense();
        driving.setCategories(drivingLicense.getCategories());
        driving.setDate_issue(drivingLicense.getDate_issue());
        driving.setDate_end(drivingLicense.getDate_end());
        return driving;
    }


    public tech.na_app.model.profile.AvailableDocuments fillDriverLicense(AvailableDocuments availableDocuments) {
        tech.na_app.model.profile.AvailableDocuments documents = new tech.na_app.model.profile.AvailableDocuments();
        documents.setEmployment_history(availableDocuments.getEmployment_history());
        documents.setIpn(availableDocuments.getIpn());
        documents.setPassport(availableDocuments.getPassport());
        documents.setMilitary_registration_doc(availableDocuments.getMilitary_registration_doc());
        documents.setHealth_certificate(availableDocuments.getHealth_certificate());
        return documents;
    }

}
