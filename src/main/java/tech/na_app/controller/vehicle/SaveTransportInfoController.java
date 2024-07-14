package tech.na_app.controller.vehicle;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;
import tech.na_app.controller.AuthenticationStrategy;
import tech.na_app.controller.BaseController;
import tech.na_app.entity.user.User;
import tech.na_app.model.enums.UserRoleType;
import tech.na_app.model.vehicle.SaveNewTransportRequest;
import tech.na_app.model.vehicle.SaveNewTransportResponse;
import tech.na_app.services.vehicle.save_data.SaveNewTransportService;

@Log4j2
@RestController
@RequestMapping("transport/save")
@RequiredArgsConstructor
public class SaveTransportInfoController extends BaseController {

    private final AuthenticationStrategy authenticationStrategy;
    private final SaveNewTransportService saveNewTransportService;

    @PostMapping("/new_transport")
    public SaveNewTransportResponse saveNewTransport(
            @RequestHeader(name = "Authorization") String token, @RequestBody SaveNewTransportRequest request
    ) {
        String requestId = generateRequestId();
        log.info(requestId + " Request to /editNomenclatureName");
        return handleRequest(requestId, () -> {
            User user = authenticationStrategy.authenticate(token, UserRoleType.SUPER_ADMIN);
            log.info(requestId + " User: " + user);
            return saveNewTransportService.addNewVehicle(requestId, request, user);
        });
    }

}
