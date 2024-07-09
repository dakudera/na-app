package tech.na_app.model.vehicle;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.na_app.model.exceptions.ErrorObject;
import tech.na_app.model.enums.EnvironmentalStandard;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetEnvironmentalStandardResponse {


    private List<GetEnvironmentalStandard> standards;

    private ErrorObject error;

    public GetEnvironmentalStandardResponse(ErrorObject error) {
        this.error = error;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GetEnvironmentalStandard {
        @Schema(
                example = "EURO 1"
        )
        private String name;

        @Schema(
                example = "EURO_1"
        )
        private EnvironmentalStandard standard;
    }


}
