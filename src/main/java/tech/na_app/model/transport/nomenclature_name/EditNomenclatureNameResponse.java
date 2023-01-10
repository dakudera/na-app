package tech.na_app.model.transport.nomenclature_name;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import tech.na_app.model.ErrorObject;

@Data
@Builder
@AllArgsConstructor
public class EditNomenclatureNameResponse {

    private ErrorObject error;
}
