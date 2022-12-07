package tech.na_app.model.transport;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.na_app.model.ErrorObject;
import tech.na_app.model.enums.TransportStatus;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetAllTransportResponse {

    private List<Transport> transports;

    private ErrorObject errorObject;

    public GetAllTransportResponse(ErrorObject errorObject) {
        this.errorObject = errorObject;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Transport {

        private Integer id;

        private String brand;

        private String state_number;

        private String nomenclature_name;

        private TransportStatus transport_status;
    }
}
