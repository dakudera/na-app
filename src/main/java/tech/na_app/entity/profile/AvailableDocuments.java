package tech.na_app.entity.profile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AvailableDocuments {

    private String passport;
    private String ipn;
    private String employment_history;
    private String military_registration_doc;
    private HealthCertificate health_certificate;

}
