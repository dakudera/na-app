package tech.na_app.model.profile.education;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EducationInfo {

    private String id;

    private String certificate;
    private String specialty;
    private String advanced_qualification;


}
