package tech.na_app.model.transport;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.na_app.model.ErrorObject;
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
        private String name;
        private Fuel fuel;
    }

}
