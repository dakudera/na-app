package tech.na_app.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;
import tech.na_app.entity.user.User;
import tech.na_app.model.ApiException;
import tech.na_app.model.ErrorObject;
import tech.na_app.model.enums.UserRole;
import tech.na_app.model.transport.SaveNewTransportRequest;
import tech.na_app.model.transport.SaveNewTransportResponse;
import tech.na_app.services.transport.TransportService;
import tech.na_app.utils.HelpUtil;
import tech.na_app.utils.jwt.AuthChecker;

@Log4j2
@RestController
@RequestMapping("transport")
@RequiredArgsConstructor
public class TransportController {

    private final AuthChecker authChecker;
    private final TransportService transportService;

    @PostMapping("/save_new_transport")
    public SaveNewTransportResponse saveNewUser(
            @RequestHeader(name = "Authorization") String token, @RequestBody SaveNewTransportRequest request
    ) {
        String requestId = HelpUtil.getUUID();
        try {
            User user = authChecker.checkToken(token, UserRole.CHIEF_ACCOUNTANT);
            log.info(requestId + " Request to saveNewTransport: " + request);
            SaveNewTransportResponse response = transportService.saveNewTransport(requestId, request, user);
            log.info(requestId + " Response: " + response);
            return response;
        } catch (ApiException e) {
            log.error(requestId + " Error: " + e.getCode() + " Message: " + e.getMessage());
            return new SaveNewTransportResponse(new ErrorObject(e.getCode(), e.getMessage()));
        }
    }
}
