package tech.na_app.services.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import tech.na_app.entity.user.User;
import tech.na_app.entity.user.UserRolesStore;
import tech.na_app.model.exceptions.ApiException;
import tech.na_app.model.exceptions.ErrorObject;
import tech.na_app.model.user.employee.EmployeeInfo;
import tech.na_app.model.user.employee.GetAllEmployeeResponse;
import tech.na_app.repository.UserRepository;
import tech.na_app.repository.UserRolesStoreRepository;

import java.util.ArrayList;
import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final UserRolesStoreRepository userRolesStoreRepository;
    private final UserRepository userRepository;

    public GetAllEmployeeResponse getAllEmployee(String requestId, User user) {
        try {
            if (user.getCompanyId() == null) {
                log.info(requestId + " User doesn't have company");
                return new GetAllEmployeeResponse(new ErrorObject(0));
            }

            List<UserRolesStore> roles = userRolesStoreRepository.findAll();
            List<User> users = userRepository.findAllByCompanyId(user.getCompanyId());

            List<EmployeeInfo> employeeInfo = new ArrayList<>();
            for (var employee : users) {

                if (employee.getId().equals(user.getId())) continue;

                String role = roles.stream()
                        .filter(
                                r -> employee.getRole().equals(r.getRole())
                        ).findAny()
                        .map(UserRolesStore::getDescription)
                        .orElse("");
                employeeInfo.add(
                        EmployeeInfo.builder()
                                .id(employee.getId().toHexString())
                                .fio(
                                        employee.getProfile() != null && employee.getProfile().getFio() != null ?
                                                employee.getProfile().getFio() : null
                                ).role(role)
                                .build()
                );
            }

            return GetAllEmployeeResponse.builder()
                    .employeeInfo(employeeInfo)
                    .error(new ErrorObject(0))
                    .build();
        } catch (ApiException e) {
            log.error(requestId + " Error: " + e.getCode() + " Message: " + e.getMessage());
            return new GetAllEmployeeResponse(new ErrorObject(e.getCode(), e.getMessage()));
        } catch (Exception e) {
            log.error(requestId + " Message: " + e.getMessage());
            return new GetAllEmployeeResponse(new ErrorObject(500, "Something went wrong"));
        }
    }

}
