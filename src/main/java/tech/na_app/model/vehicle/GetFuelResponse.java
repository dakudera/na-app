package tech.na_app.model.vehicle;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.na_app.model.exceptions.ErrorObject;
import tech.na_app.model.enums.Fuel;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetFuelResponse {


    private List<GetFuel> fuels;

    private ErrorObject error;

    public GetFuelResponse(ErrorObject error) {
        this.error = error;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GetFuel {
        @Schema(
                example = "Gasoline"
        )
        private String name;
        @Schema(
                example = "GASOLINE"
        )
        private Fuel fuel;

    }

}
