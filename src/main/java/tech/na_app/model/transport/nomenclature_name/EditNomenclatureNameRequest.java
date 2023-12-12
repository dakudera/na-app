package tech.na_app.model.transport.nomenclature_name;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EditNomenclatureNameRequest {

    @NotNull
    private Integer id;

    @NotEmpty
    private String nomenclature_name;
}
