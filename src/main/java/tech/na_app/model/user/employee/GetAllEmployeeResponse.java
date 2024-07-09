package tech.na_app.model.user.employee;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.na_app.model.exceptions.ErrorObject;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetAllEmployeeResponse {


    private List<EmployeeInfo> employeeInfo;

    private ErrorObject error;


    public GetAllEmployeeResponse(ErrorObject error) {
        this.error = error;
    }
}
