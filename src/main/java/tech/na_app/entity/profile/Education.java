package tech.na_app.entity.profile;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "education")
public class Education {

    @MongoId
    private ObjectId id;

    private String certificate;
    private String specialty;
    private String advanced_qualification;

    private ObjectId userId;

}
