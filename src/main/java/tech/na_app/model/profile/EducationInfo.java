package tech.na_app.model.profile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EducationInfo {

    private Integer id;

    private String certificate;
    private String specialty;
    private String advanced_qualification;


}
