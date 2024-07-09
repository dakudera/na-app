package tech.na_app.model.vehicle.nomenclature_name;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import tech.na_app.model.exceptions.ErrorObject;

@Data
@Builder
@AllArgsConstructor
public class EditNomenclatureNameResponse {

    private ErrorObject error;
}
