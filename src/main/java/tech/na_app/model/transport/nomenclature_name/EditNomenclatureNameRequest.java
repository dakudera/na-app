package tech.na_app.model.transport.nomenclature_name;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class EditNomenclatureNameRequest {

    private Integer id;

    private String nomenclature_name;
}
