package tech.na_app.services.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import tech.na_app.entity.user.UserRolesStore;
import tech.na_app.model.ErrorObject;
import tech.na_app.model.user.GetAllUserRolesResponse;
import tech.na_app.repository.UserRolesStoreRepository;

import java.util.ArrayList;
import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class GetAllUserRolesServiceImpl implements GetAllUserRolesService {

    private final UserRolesStoreRepository userRolesStoreRepository;

    @Override
    public GetAllUserRolesResponse getAllUserRoles(String requestId) {
        try {
            List<UserRolesStore> all = userRolesStoreRepository.findAll();
            GetAllUserRolesResponse response = new GetAllUserRolesResponse(new ErrorObject(0));
            List<GetAllUserRolesResponse.Role> roles = new ArrayList<>();
            all.forEach(a -> roles.add(
                    GetAllUserRolesResponse.Role.builder()
                            .role(a.getRole())
                            .description(a.getDescription())
                            .build()
            ));

            response.setRoles(roles);
            return response;
        } catch (Exception e) {
            log.error(requestId + " Error: " + e.getMessage());
            return new GetAllUserRolesResponse(new ErrorObject(500, e.getMessage()));
        }
    }
}
