package tech.na_app.services.user;

import tech.na_app.entity.user.User;
import tech.na_app.model.user.employee.GetAllEmployeeResponse;

public interface EmployeeService {

    GetAllEmployeeResponse getAllEmployee(String requestId, User user);

}
