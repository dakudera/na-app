package tech.na_app.services.vehicle.edit_data;

import tech.na_app.model.vehicle.nomenclature_name.EditNomenclatureNameRequest;
import tech.na_app.model.vehicle.nomenclature_name.EditNomenclatureNameResponse;

public interface EditNomenclatureNameService {

    EditNomenclatureNameResponse editNomenclatureName(String requestId, EditNomenclatureNameRequest request);

}
