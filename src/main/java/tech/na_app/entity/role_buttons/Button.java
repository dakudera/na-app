package tech.na_app.entity.role_buttons;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.na_app.model.enums.ButtonType;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Button {

    @Schema(example = "Main")
    private String title;
    @Schema(example = "MAIN")
    private ButtonType button;
    @Schema(example = "/rote")
    private String rote;

}
