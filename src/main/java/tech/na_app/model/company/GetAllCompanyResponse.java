package tech.na_app.model.company;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import tech.na_app.model.exceptions.ErrorObject;

import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetAllCompanyResponse {

    private List<Company> companies;

    private ErrorObject errorObject;

    public GetAllCompanyResponse(ErrorObject errorObject) {
        this.errorObject = errorObject;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Company {
        private ObjectId id;
        private String name;

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
        private Date registrationDate;
    }

}
