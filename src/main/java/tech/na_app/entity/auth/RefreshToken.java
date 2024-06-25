package tech.na_app.entity.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import javax.persistence.Transient;
import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "refresh_token")
public class RefreshToken {

    @Transient
    public static final String SEQUENCE_NAME = "refresh_token_sequence";

    @MongoId
    private ObjectId id;
    private ObjectId userId;
    private String token;
    private Instant expiryDate;

}
