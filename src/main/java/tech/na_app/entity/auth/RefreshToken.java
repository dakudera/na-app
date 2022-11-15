package tech.na_app.entity.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
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

    @Id
    private Integer id;
    private Integer userId;
    private String token;
    private Instant expiryDate;

}
