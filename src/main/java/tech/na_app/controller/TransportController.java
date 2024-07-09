package tech.na_app.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;
import tech.na_app.entity.user.User;
import tech.na_app.model.exceptions.ApiException;
import tech.na_app.model.exceptions.ErrorObject;
import tech.na_app.model.enums.UserRoleType;
import tech.na_app.model.vehicle.*;
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
import tech.na_app.services.vehicle.get_data.GetAllTransportService;
import tech.na_app.services.vehicle.get_data.GetEnvironmentalStandardService;
import tech.na_app.services.vehicle.get_data.GetFuelService;
import tech.na_app.services.vehicle.get_data.GetTransportInfoService;
import tech.na_app.services.vehicle.save_data.SaveNewTransportService;
import tech.na_app.utils.HelpUtil;
import tech.na_app.utils.ValidateHelper;
import tech.na_app.utils.jwt.AuthChecker;


@Log4j2
@RestController
@RequestMapping("transport")
@RequiredArgsConstructor
public class TransportController {

    private final AuthChecker authChecker;
    private final GetEnvironmentalStandardService getEnvironmentalStandardService;
    private final SaveNewTransportService saveNewTransportService;
    private final GetAllTransportService getAllTransportService;
    private final EditTransportGeneralInfoService editTransportGeneralInfoService;
    private final EditTransportUsingReasonInfoService editTransportUsingReasonInfoService;
    private final GetTransportInfoService getTransportInfoService;
    private final EditTechnicalCertificateService editTechnicalCertificateService;
    private final EditNomenclatureNameService editNomenclatureNameService;
    private final EditTechnicalCertificateDopInfoService editTechnicalCertificateDopInfoService;
    private final GetFuelService getFuelService;

    @PostMapping("/save_new_transport")
    public SaveNewTransportResponse saveNewTransport(
            @RequestHeader(name = "Authorization") String token, @RequestBody SaveNewTransportRequest request
    ) {
        String requestId = HelpUtil.getUUID();
        try {
            User user = authChecker.checkToken(token, UserRoleType.CHIEF_ACCOUNTANT);
            ValidateHelper.validateInput(request);
            log.info(requestId + " Request to /saveNewTransport: " + request);
            log.info(requestId + " User: " + user);
            SaveNewTransportResponse response = saveNewTransportService.addNewVehicle(requestId, request, user);
            log.info(requestId + " Response from /saveNewTransport: " + response);
            return response;
        } catch (ApiException e) {
            log.error(requestId + " Error: " + e.getCode() + " Message: " + e.getMessage());
            return new SaveNewTransportResponse(new ErrorObject(e.getCode(), e.getMessage()));
        }
    }

    @GetMapping("/get_all_transport")
    public GetAllTransportResponse getAllTransport(@RequestHeader(name = "Authorization") String token) {
        String requestId = HelpUtil.getUUID();
        try {
            User user = authChecker.checkToken(token, UserRoleType.CHIEF_ACCOUNTANT);
            log.info(requestId + " Request to /getAllTransport");
            log.info(requestId + " User: " + user);
            GetAllTransportResponse response = getAllTransportService.getAllTransport(requestId, user);
            log.info(requestId + " Response from /getAllTransport: " + response);
            return response;
        } catch (ApiException e) {
            log.error(requestId + " Error: " + e.getCode() + " Message: " + e.getMessage());
            return new GetAllTransportResponse(new ErrorObject(e.getCode(), e.getMessage()));
        }
    }

    @GetMapping("/get_transport_info")
    public GetTransportInfoResponse getTransportInfo(
            @RequestHeader(name = "Authorization") String token, @RequestBody GetTransportInfoRequest request
    ) {
        String requestId = HelpUtil.getUUID();
        try {
            User user = authChecker.checkToken(token, UserRoleType.CHIEF_ACCOUNTANT);
            ValidateHelper.validateInput(request);
            log.info(requestId + " Request to /getTransportInfo");
            log.info(requestId + " User: " + user);
            GetTransportInfoResponse response = getTransportInfoService.getTransportInfo(requestId, user, request);
            log.info(requestId + " Response from /getTransportInfo: " + response);
            return response;
        } catch (ApiException e) {
            log.error(requestId + " Error: " + e.getCode() + " Message: " + e.getMessage());
            return new GetTransportInfoResponse(new ErrorObject(e.getCode(), e.getMessage()));
        }
    }

    @PutMapping("edit/general_info")
    public EditTransportGeneralInfoResponse editGeneralInfo(
            @RequestHeader(name = "Authorization") String token, @RequestBody EditTransportGeneralInfoRequest request
    ) {
        String requestId = HelpUtil.getUUID();
        try {
            User user = authChecker.checkToken(token, UserRoleType.CHIEF_ACCOUNTANT);
            ValidateHelper.validateInput(request);
            log.info(requestId + " Request to /editGeneralInfo");
            log.info(requestId + " User: " + user);
            EditTransportGeneralInfoResponse response = editTransportGeneralInfoService.editTransportGeneralInfo(requestId, request);
            log.info(requestId + " Response from /editGeneralInfo: " + response);
            return response;
        } catch (ApiException e) {
            log.error(requestId + " Error: " + e.getCode() + " Message: " + e.getMessage());
            return new EditTransportGeneralInfoResponse(new ErrorObject(e.getCode(), e.getMessage()));
        }
    }

    @PutMapping("edit/using_reason_info")
    public EditTransportUsingReasonInfoResponse editUsingReasonInfo(
            @RequestHeader(name = "Authorization") String token, @RequestBody EditTransportUsingReasonInfoRequest request
    ) {
        String requestId = HelpUtil.getUUID();
        try {
            User user = authChecker.checkToken(token, UserRoleType.CHIEF_ACCOUNTANT);
            ValidateHelper.validateInput(request);
            log.info(requestId + " Request to /editUsingReasonInfo");
            log.info(requestId + " User: " + user);
            EditTransportUsingReasonInfoResponse response = editTransportUsingReasonInfoService.editTransportUsingReasonInfo(requestId, request);
            log.info(requestId + " Response from /editUsingReasonInfo: " + response);
            return response;
        } catch (ApiException e) {
            log.error(requestId + " Error: " + e.getCode() + " Message: " + e.getMessage());
            return new EditTransportUsingReasonInfoResponse(new ErrorObject(e.getCode(), e.getMessage()));
        }
    }

    @PutMapping("edit/technical_certificate")
    public EditTechnicalCertificateResponse editTechnicalCertificate(
            @RequestHeader(name = "Authorization") String token, @RequestBody EditTechnicalCertificateRequest request
    ) {
        String requestId = HelpUtil.getUUID();
        try {
            User user = authChecker.checkToken(token, UserRoleType.CHIEF_ACCOUNTANT);
            ValidateHelper.validateInput(request);
            log.info(requestId + " Request to /editTechnicalCertificate: " + request);
            log.info(requestId + " User: " + user);
            EditTechnicalCertificateResponse response = editTechnicalCertificateService.editTechnicalCertificate(requestId, request);
            log.info(requestId + " Response from /editTechnicalCertificate: " + response);
            return response;
        } catch (ApiException e) {
            log.error(requestId + " Error: " + e.getCode() + " Message: " + e.getMessage());
            return new EditTechnicalCertificateResponse(new ErrorObject(e.getCode(), e.getMessage()));
        }
    }

    @PutMapping("edit/nomenclature_name")
    public EditNomenclatureNameResponse editNomenclatureName(
            @RequestHeader(name = "Authorization") String token, @RequestBody EditNomenclatureNameRequest request
    ) {
        String requestId = HelpUtil.getUUID();
        try {
            User user = authChecker.checkToken(token, UserRoleType.CHIEF_ACCOUNTANT);
            ValidateHelper.validateInput(request);
            log.info(requestId + " Request to /editNomenclatureName: " + request);
            log.info(requestId + " User: " + user);
            EditNomenclatureNameResponse response = editNomenclatureNameService.editNomenclatureName(requestId, request);
            log.info(requestId + " Response from /editNomenclatureName: " + response);
            return response;
        } catch (ApiException e) {
            log.error(requestId + " Error: " + e.getCode() + " Message: " + e.getMessage());
            return new EditNomenclatureNameResponse(new ErrorObject(e.getCode(), e.getMessage()));
        }
    }

    @PutMapping("edit/technical_certificate_dop_info")
    public EditTechnicalCertificateDopInfoResponse editTechnicalCertificateDopInfo(
            @RequestHeader(name = "Authorization") String token, @RequestBody EditTechnicalCertificateDopInfoRequest request
    ) {
        String requestId = HelpUtil.getUUID();
        try {
            User user = authChecker.checkToken(token, UserRoleType.CHIEF_ACCOUNTANT);
            ValidateHelper.validateInput(request);
            log.info(requestId + " Request to /editTechnicalCertificateDopInfo: " + request);
            log.info(requestId + " User: " + user);
            EditTechnicalCertificateDopInfoResponse response = editTechnicalCertificateDopInfoService.editTechnicalCertificateDopInfo(requestId, request);
            log.info(requestId + " Response from /editTechnicalCertificateDopInfo: " + response);
            return response;
        } catch (ApiException e) {
            log.error(requestId + " Error: " + e.getCode() + " Message: " + e.getMessage());
            return new EditTechnicalCertificateDopInfoResponse(new ErrorObject(e.getCode(), e.getMessage()));
        }
    }

    @GetMapping("fuels")
    public GetFuelResponse getFuels(
            @RequestHeader(name = "Authorization") String token
    ) {
        String requestId = HelpUtil.getUUID();
        try {
            User user = authChecker.checkToken(token, UserRoleType.WAREHOUSE_MANAGER);
            log.info(requestId + " Request to /getFuels ");
            log.info(requestId + " User: " + user);
            GetFuelResponse response = getFuelService.getFuel(requestId);
            log.info(requestId + " Response from /getFuels: " + response);
            return response;
        } catch (ApiException e) {
            log.error(requestId + " Error: " + e.getCode() + " Message: " + e.getMessage());
            return new GetFuelResponse(new ErrorObject(e.getCode(), e.getMessage()));
        }
    }

    @GetMapping("environmental_standard")
    public GetEnvironmentalStandardResponse getEnvironmentalStandard(
            @RequestHeader(name = "Authorization") String token
    ) {
        String requestId = HelpUtil.getUUID();
        try {
            User user = authChecker.checkToken(token, UserRoleType.WAREHOUSE_MANAGER);
            log.info(requestId + " Request to /getEnvironmentalStandard ");
            log.info(requestId + " User: " + user);
            GetEnvironmentalStandardResponse response = getEnvironmentalStandardService.getEnvironmentalStandard(requestId);
            log.info(requestId + " Response from /getEnvironmentalStandard: " + response);
            return response;
        } catch (ApiException e) {
            log.error(requestId + " Error: " + e.getCode() + " Message: " + e.getMessage());
            return new GetEnvironmentalStandardResponse(new ErrorObject(e.getCode(), e.getMessage()));
        }
    }

}
