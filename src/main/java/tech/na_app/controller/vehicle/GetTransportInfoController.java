package tech.na_app.controller.vehicle;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;
import tech.na_app.controller.AuthenticationStrategy;
import tech.na_app.controller.BaseController;
import tech.na_app.entity.user.User;
import tech.na_app.model.enums.UserRoleType;
import tech.na_app.model.vehicle.*;
import tech.na_app.services.vehicle.get_data.GetAllTransportService;
import tech.na_app.services.vehicle.get_data.GetEnvironmentalStandardService;
import tech.na_app.services.vehicle.get_data.GetFuelService;
import tech.na_app.services.vehicle.get_data.GetTransportInfoService;
import tech.na_app.utils.LineUtil;

@Log4j2
@RestController
@RequestMapping("transport")
@RequiredArgsConstructor
public class GetTransportInfoController extends BaseController {

    private final AuthenticationStrategy authenticationStrategy;
    private final GetEnvironmentalStandardService getEnvironmentalStandardService;
    private final GetAllTransportService getAllTransportService;
    private final GetTransportInfoService getTransportInfoService;
    private final GetFuelService getFuelService;

    @GetMapping("/get_all_transport")
    public GetAllTransportResponse getAllTransport(
            @RequestHeader(name = "Authorization") String token
    ) {
        String requestId = generateRequestId();
        log.info(requestId + " Request to /getAllTransport");
        return handleRequest(requestId, () -> {
            User user = authenticationStrategy.authenticate(token, UserRoleType.SUPER_ADMIN);
            log.info(requestId + LineUtil.USER + user);
            return getAllTransportService.getAllTransport(requestId, user);
        });
    }

    @GetMapping("/get_transport_info")
    public GetTransportInfoResponse getTransportInfo(
            @RequestHeader(name = "Authorization") String token,
            @RequestBody GetTransportInfoRequest request
    ) {
        String requestId = generateRequestId();
        log.info(requestId + " Request to /getTransportInfo");
        return handleRequest(requestId, () -> {
            User user = authenticationStrategy.authenticate(token, UserRoleType.SUPER_ADMIN);
            log.info(requestId + LineUtil.USER + user);
            return getTransportInfoService.getTransportInfo(requestId, user, request);
        });
    }


    @GetMapping("fuels")
    public GetFuelResponse getFuels(
            @RequestHeader(name = "Authorization") String token
    ) {
        String requestId = generateRequestId();
        log.info(requestId + " Request to /getFuels");
        return handleRequest(requestId, () -> {
            User user = authenticationStrategy.authenticate(token, UserRoleType.SUPER_ADMIN);
            log.info(requestId + LineUtil.USER + user);
            return getFuelService.getFuel(requestId);
        });
    }

    @GetMapping("environmental_standard")
    public GetEnvironmentalStandardResponse getEnvironmentalStandard(
            @RequestHeader(name = "Authorization") String token
    ) {
        String requestId = generateRequestId();
        log.info(requestId + " Request to /getEnvironmentalStandard");
        return handleRequest(requestId, () -> {
            User user = authenticationStrategy.authenticate(token, UserRoleType.SUPER_ADMIN);
            log.info(requestId + LineUtil.USER + user);
            return getEnvironmentalStandardService.getEnvironmentalStandard(requestId);
        });
    }

}
