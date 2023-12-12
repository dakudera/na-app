package tech.na_app.model.company.identification_detalis;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EditIdentificationDetailsRequest {

    @NotNull
    private IdentificationDetails identification_details;
}
