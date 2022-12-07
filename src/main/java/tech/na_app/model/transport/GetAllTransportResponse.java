package tech.na_app.model.transport;

import com.fasterxml.jackson.annotation.JsonProperty;
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

    @JsonProperty("transports")
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

        @JsonProperty("id")
        private Integer id;

        @JsonProperty("brand")
        private String brand;

        @JsonProperty("state_number")
        private String state_number;

        @JsonProperty("nomenclature_name")
        private String nomenclature_name;

        @JsonProperty("transport_status")
        private TransportStatus transport_status;
    }
}
