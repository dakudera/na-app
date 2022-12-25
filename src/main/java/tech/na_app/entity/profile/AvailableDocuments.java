package tech.na_app.entity.profile;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Document(collection = "available_documents")
public class AvailableDocuments {

    @Id
    private String id;

    @Schema(
            example = "AE123456",
            type = "string"
    )
    private String passport;

    @Schema(
            example = "1234567890",
            type = "string"
    )
    private String ipn;

    @Schema(
            example = "1231231",
            type = "string"
    )
    private String employment_history;

    @Schema(
            example = "12313",
            type = "string"
    )
    private String military_registration_doc;
    private HealthCertificate health_certificate;
    private Integer userId;

}
