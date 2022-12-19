package tech.na_app.entity.profile;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "education_sequence")
public class AvailableDocumentsSequence {

    @Id
    private String id;

    private Integer seq;

}
