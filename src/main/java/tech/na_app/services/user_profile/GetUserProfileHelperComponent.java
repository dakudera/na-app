package tech.na_app.services.user_profile;

import org.springframework.stereotype.Component;
import tech.na_app.entity.profile.Education;
import tech.na_app.entity.profile.InternshipAndInstruction;
import tech.na_app.entity.profile.Profile;
import tech.na_app.entity.user.User;
import tech.na_app.model.profile.EducationInfo;
import tech.na_app.model.profile.GetUserProfileResponse;
import tech.na_app.model.profile.InstructionInfo;

import java.util.ArrayList;
import java.util.List;

@Component
public class GetUserProfileHelperComponent {

    List<EducationInfo> buildEducations(List<Education> educations) {
        List<EducationInfo> educationInfo = new ArrayList<>();
        if (educations == null || educations.isEmpty()) {
            return educationInfo;
        }

        educations.forEach(
                e -> educationInfo.add(
                        EducationInfo.builder()
                                .id(e.getId())
                                .advanced_qualification(e.getAdvanced_qualification())
                                .certificate(e.getCertificate())
                                .specialty(e.getSpecialty())
                                .build()
                )
        );
        return educationInfo;
    }

    List<InstructionInfo> buildInstructionsAndInternships(List<InternshipAndInstruction> instructions) {
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

    void fillUserProfile(User userInfo, GetUserProfileResponse response) {
        Profile profile = userInfo.getProfile();
        response.setEmail(profile.getEmail());
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

}
