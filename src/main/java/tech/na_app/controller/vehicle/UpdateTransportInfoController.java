package tech.na_app.controller.vehicle;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;
import tech.na_app.controller.AuthenticationStrategy;
import tech.na_app.controller.BaseController;
import tech.na_app.entity.user.User;
import tech.na_app.model.enums.UserRoleType;
import tech.na_app.model.vehicle.general_info.EditTransportGeneralInfoRequest;
import tech.na_app.model.vehicle.general_info.EditTransportGeneralInfoResponse;
import tech.na_app.model.vehicle.nomenclature_name.EditNomenclatureNameRequest;
import tech.na_app.model.vehicle.nomenclature_name.EditNomenclatureNameResponse;
import tech.na_app.model.vehicle.technical_certificate.EditTechnicalCertificateRequest;
import tech.na_app.model.vehicle.technical_certificate.EditTechnicalCertificateResponse;
import tech.na_app.model.vehicle.technical_certificate_dop_info.EditTechnicalCertificateDopInfoRequest;
import tech.na_app.model.vehicle.technical_certificate_dop_info.EditTechnicalCertificateDopInfoResponse;
import tech.na_app.model.vehicle.using_reason.EditTransportUsingReasonInfoRequest;
import tech.na_app.model.vehicle.using_reason.EditTransportUsingReasonInfoResponse;
import tech.na_app.services.vehicle.edit_data.*;

@Log4j2
@RestController
@RequestMapping("transport/update")
@RequiredArgsConstructor
public class UpdateTransportInfoController extends BaseController {


    private final AuthenticationStrategy authenticationStrategy;
    private final EditTransportGeneralInfoService editTransportGeneralInfoService;
    private final EditTransportUsingReasonInfoService editTransportUsingReasonInfoService;
    private final EditTechnicalCertificateService editTechnicalCertificateService;
    private final EditNomenclatureNameService editNomenclatureNameService;
    private final EditTechnicalCertificateDopInfoService editTechnicalCertificateDopInfoService;

    @PutMapping("/general_info")
    public EditTransportGeneralInfoResponse editGeneralInfo(
            @RequestHeader(name = "Authorization") String token, @RequestBody EditTransportGeneralInfoRequest request
    ) {
        String requestId = generateRequestId();
        log.info(requestId + " Request to /editGeneralInfo");
        return handleRequest(requestId, () -> {
            User user = authenticationStrategy.authenticate(token, UserRoleType.SUPER_ADMIN);
            log.info(requestId + " User: " + user);
            return editTransportGeneralInfoService.editTransportGeneralInfo(requestId, request);
        });
    }

    @PutMapping("/using_reason_info")
    public EditTransportUsingReasonInfoResponse editUsingReasonInfo(
            @RequestHeader(name = "Authorization") String token, @RequestBody EditTransportUsingReasonInfoRequest request
    ) {
        String requestId = generateRequestId();
        log.info(requestId + " Request to /editUsingReasonInfo");
        return handleRequest(requestId, () -> {
            User user = authenticationStrategy.authenticate(token, UserRoleType.SUPER_ADMIN);
            log.info(requestId + " User: " + user);
            return editTransportUsingReasonInfoService.editTransportUsingReasonInfo(requestId, request);
        });
    }

    @PutMapping("/technical_certificate")
    public EditTechnicalCertificateResponse editTechnicalCertificate(
            @RequestHeader(name = "Authorization") String token, @RequestBody EditTechnicalCertificateRequest request
    ) {
        String requestId = generateRequestId();
        log.info(requestId + " Request to /editTechnicalCertificate");
        return handleRequest(requestId, () -> {
            User user = authenticationStrategy.authenticate(token, UserRoleType.SUPER_ADMIN);
            log.info(requestId + " User: " + user);
            return editTechnicalCertificateService.editTechnicalCertificate(requestId, request);
        });
    }

    @PutMapping("/nomenclature_name")
    public EditNomenclatureNameResponse editNomenclatureName(
            @RequestHeader(name = "Authorization") String token, @RequestBody EditNomenclatureNameRequest request
    ) {
        String requestId = generateRequestId();
        log.info(requestId + " Request to /editNomenclatureName");
        return handleRequest(requestId, () -> {
            User user = authenticationStrategy.authenticate(token, UserRoleType.SUPER_ADMIN);
            log.info(requestId + " User: " + user);
            return editNomenclatureNameService.editNomenclatureName(requestId, request);
        });
    }

    @PutMapping("/technical_certificate_dop_info")
    public EditTechnicalCertificateDopInfoResponse editTechnicalCertificateDopInfo(
            @RequestHeader(name = "Authorization") String token, @RequestBody EditTechnicalCertificateDopInfoRequest request
    ) {
        String requestId = generateRequestId();
        log.info(requestId + " Request to /editTechnicalCertificateDopInfo");
        return handleRequest(requestId, () -> {
            User user = authenticationStrategy.authenticate(token, UserRoleType.SUPER_ADMIN);
            log.info(requestId + " User: " + user);
            return editTechnicalCertificateDopInfoService.editTechnicalCertificateDopInfo(requestId, request);
        });
    }

}
