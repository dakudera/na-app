package tech.na_app.services.transport.edit_data;

import tech.na_app.model.transport.nomenclature_name.EditNomenclatureNameRequest;
import tech.na_app.model.transport.nomenclature_name.EditNomenclatureNameResponse;

public interface EditNomenclatureNameService {

    EditNomenclatureNameResponse editNomenclatureName(String requestId, EditNomenclatureNameRequest request);

}
