package tech.na_app.entity.profile;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import javax.persistence.Transient;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "education")
public class Education {

    @Transient
    public static final String SEQUENCE_NAME = "education_sequence";

    @Id
    private Integer id;

    private String certificate;
    private String specialty;
    private String advanced_qualification;

    private Integer userId;

}
