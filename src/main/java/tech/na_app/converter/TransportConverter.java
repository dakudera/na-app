package tech.na_app.converter;

import org.springframework.stereotype.Component;
import tech.na_app.entity.transport.*;
import tech.na_app.entity.user.User;
import tech.na_app.model.enums.TransportStatus;
import tech.na_app.model.vehicle.GetAllTransportResponse;
import tech.na_app.model.vehicle.GetTransportInfoResponse;
import tech.na_app.model.vehicle.SaveNewTransportRequest;
import tech.na_app.model.vehicle.technical_certificate_dop_info.EditTechnicalCertificateDopInfoRequest;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class TransportConverter {

    public Transport convertToTransportEntity(SaveNewTransportRequest request, TransportSequence transportSequence, User user) {
        return Transport.builder()
                .id(transportSequence.getSeq())
                .create_date(new Date())
                .transport_status(TransportStatus.PARKING)
                .transport_card(Objects.nonNull(request.getTransport_card()) ? TransportCard
                        .builder()
                        .nomenclature_name(request.getTransport_card().getNomenclature_name())
                        .technical_certificate(Objects.nonNull(request.getTransport_card().getTechnical_certificate()) ? TechnicalCertificate
                                .builder()
                                .num_and_series(request.getTransport_card()
                                        .getTechnical_certificate().getNum_and_series())
                                .issued_by(request.getTransport_card()
                                        .getTechnical_certificate().getIssued_by())
                                .date_end(request.getTransport_card()
                                        .getTechnical_certificate().getDate_end())
                                .date_issue(request.getTransport_card()
                                        .getTechnical_certificate().getDate_issue())
                                .technical_certificate_dop_info(Objects.nonNull(request.getTransport_card()
                                        .getTechnical_certificate().getTechnical_certificate_dop_info()) ? TechnicalCertificateDopInfo
                                        .builder()
                                        .brand(request.getTransport_card()
                                                .getTechnical_certificate().getTechnical_certificate_dop_info().getBrand())
                                        .state_number(request.getTransport_card()
                                                .getTechnical_certificate().getTechnical_certificate_dop_info().getState_number())
                                        .VIN_code(request.getTransport_card()
                                                .getTechnical_certificate().getTechnical_certificate_dop_info().getVIN_code())
                                        .colour(request.getTransport_card()
                                                .getTechnical_certificate().getTechnical_certificate_dop_info().getColour())
                                        .date_issue(request.getTransport_card()
                                                .getTechnical_certificate().getTechnical_certificate_dop_info().getDate_issue())
                                        .seats(request.getTransport_card()
                                                .getTechnical_certificate().getTechnical_certificate_dop_info().getSeats())
                                        .full_weight(request.getTransport_card()
                                                .getTechnical_certificate().getTechnical_certificate_dop_info().getFull_weight())
                                        .empty_weight(request.getTransport_card()
                                                .getTechnical_certificate().getTechnical_certificate_dop_info().getEmpty_weight())
                                        .category(request.getTransport_card()
                                                .getTechnical_certificate().getTechnical_certificate_dop_info().getCategory())
                                        .fuel(request.getTransport_card()
                                                .getTechnical_certificate().getTechnical_certificate_dop_info().getFuel())
                                        .body_type(request.getTransport_card()
                                                .getTechnical_certificate().getTechnical_certificate_dop_info().getBody_type())
                                        .engine_volume(request.getTransport_card()
                                                .getTechnical_certificate().getTechnical_certificate_dop_info().getEngine_volume())
                                        .engine_power(request.getTransport_card()
                                                .getTechnical_certificate().getTechnical_certificate_dop_info().getEngine_power())
                                        .environmental_standard(request.getTransport_card()
                                                .getTechnical_certificate().getTechnical_certificate_dop_info().getEnvironmental_standard())
                                        .build() : null)
                                .build() : null)
                        .using_reason_info(Objects.nonNull(request.getTransport_card().getUsing_reason_info()) ? UsingReasonInfo
                                .builder()
                                .num_and_name_contract(request.getTransport_card()
                                        .getUsing_reason_info().getNum_and_name_contract())
                                .date_start(request.getTransport_card()
                                        .getUsing_reason_info().getDate_start())
                                .is_contract_fixed_term(request.getTransport_card()
                                        .getUsing_reason_info().getIs_contract_fixed_term())
                                .date_end(request.getTransport_card()
                                        .getUsing_reason_info().getDate_end())
                                .date_next_start(request.getTransport_card()
                                        .getUsing_reason_info().getDate_next_start())
                                .build() : null)
                        .general_info(Objects.nonNull(request.getTransport_card().getGeneral_info()) ? GeneralInfo
                                .builder()
                                .mileage(request.getTransport_card().getGeneral_info().getMileage())
                                .fuel_tank_volume(request.getTransport_card().getGeneral_info().getFuel_tank_volume())
                                .height(request.getTransport_card().getGeneral_info().getHeight())
                                .width(request.getTransport_card().getGeneral_info().getWidth())
                                .length(request.getTransport_card().getGeneral_info().getLength())
                                .build() : null)
                        .build() : null)
                .companyId(user.getCompanyId())
                .build();
    }


    public GetTransportInfoResponse convertToTransportModel(Transport transport) {
        return GetTransportInfoResponse.builder()
                .nomenclature_name(transport.getTransport_card().getNomenclature_name())
                .technical_certificate(Objects.nonNull(transport.getTransport_card().getTechnical_certificate())
                        ? tech.na_app.model.vehicle.technical_certificate.TechnicalCertificate
                        .builder()
                        .num_and_series(transport.getTransport_card()
                                .getTechnical_certificate().getNum_and_series())
                        .issued_by(transport.getTransport_card()
                                .getTechnical_certificate().getIssued_by())
                        .date_end(transport.getTransport_card()
                                .getTechnical_certificate().getDate_end())
                        .date_issue(transport.getTransport_card()
                                .getTechnical_certificate().getDate_issue())
                        .technical_certificate_dop_info(Objects.nonNull(transport.getTransport_card()
                                .getTechnical_certificate().getTechnical_certificate_dop_info())
                                ? tech.na_app.model.vehicle.technical_certificate_dop_info.TechnicalCertificateDopInfo.builder()
                                .brand(transport.getTransport_card()
                                        .getTechnical_certificate().getTechnical_certificate_dop_info().getBrand())
                                .state_number(transport.getTransport_card()
                                        .getTechnical_certificate().getTechnical_certificate_dop_info().getState_number())
                                .VIN_code(transport.getTransport_card()
                                        .getTechnical_certificate().getTechnical_certificate_dop_info().getVIN_code())
                                .colour(transport.getTransport_card()
                                        .getTechnical_certificate().getTechnical_certificate_dop_info().getColour())
                                .date_issue(transport.getTransport_card()
                                        .getTechnical_certificate().getTechnical_certificate_dop_info().getDate_issue())
                                .seats(transport.getTransport_card()
                                        .getTechnical_certificate().getTechnical_certificate_dop_info().getSeats())
                                .full_weight(transport.getTransport_card()
                                        .getTechnical_certificate().getTechnical_certificate_dop_info().getFull_weight())
                                .empty_weight(transport.getTransport_card()
                                        .getTechnical_certificate().getTechnical_certificate_dop_info().getEmpty_weight())
                                .category(transport.getTransport_card()
                                        .getTechnical_certificate().getTechnical_certificate_dop_info().getCategory())
                                .fuel(transport.getTransport_card()
                                        .getTechnical_certificate().getTechnical_certificate_dop_info().getFuel())
                                .body_type(transport.getTransport_card()
                                        .getTechnical_certificate().getTechnical_certificate_dop_info().getBody_type())
                                .engine_volume(transport.getTransport_card()
                                        .getTechnical_certificate().getTechnical_certificate_dop_info().getEngine_volume())
                                .engine_power(transport.getTransport_card()
                                        .getTechnical_certificate().getTechnical_certificate_dop_info().getEngine_power())
                                .environmental_standard(transport.getTransport_card()
                                        .getTechnical_certificate().getTechnical_certificate_dop_info().getEnvironmental_standard())
                                .build() : null)
                        .build() : null)
                .using_reason_info(Objects.nonNull(transport.getTransport_card().getUsing_reason_info())
                        ? tech.na_app.model.vehicle.using_reason.UsingReasonInfo.builder()
                        .num_and_name_contract(transport.getTransport_card()
                                .getUsing_reason_info().getNum_and_name_contract())
                        .date_start(transport.getTransport_card()
                                .getUsing_reason_info().getDate_start())
                        .is_contract_fixed_term(transport.getTransport_card()
                                .getUsing_reason_info().getIs_contract_fixed_term())
                        .date_end(transport.getTransport_card()
                                .getUsing_reason_info().getDate_end())
                        .date_next_start(transport.getTransport_card()
                                .getUsing_reason_info().getDate_next_start())
                        .build() : null)
                .general_info(Objects.nonNull(transport.getTransport_card().getGeneral_info())
                        ? tech.na_app.model.vehicle.general_info.GeneralInfo.builder()
                        .mileage(transport.getTransport_card().getGeneral_info().getMileage())
                        .fuel_tank_volume(transport.getTransport_card().getGeneral_info().getFuel_tank_volume())
                        .height(transport.getTransport_card().getGeneral_info().getHeight())
                        .width(transport.getTransport_card().getGeneral_info().getWidth())
                        .length(transport.getTransport_card().getGeneral_info().getLength())
                        .build() : null)
                .build();
    }

    public List<GetAllTransportResponse.Transport> convertToTransports(List<Transport> transports) {
        return transports.stream().map(this::convertToTransport).collect(Collectors.toList());
    }

    public GetAllTransportResponse.Transport convertToTransport(Transport transport) {
        String brand = null, state_number = null, nomenclature_name = null;

        if (transport.getTransport_card() != null) {
            nomenclature_name = transport.getTransport_card().getNomenclature_name();
            if (transport.getTransport_card().getTechnical_certificate() != null
                    && transport.getTransport_card().getTechnical_certificate().getTechnical_certificate_dop_info() != null) {
                brand = transport.getTransport_card().getTechnical_certificate().getTechnical_certificate_dop_info().getBrand();
                state_number = transport.getTransport_card().getTechnical_certificate().getTechnical_certificate_dop_info().getState_number();
            }
        }

        return GetAllTransportResponse.Transport.builder()
                .id(transport.getId())
                .brand(brand)
                .state_number(state_number)
                .nomenclature_name(nomenclature_name)
                .transport_status(transport.getTransport_status())
                .build();
    }

    public TechnicalCertificateDopInfo convertToTechnicalCertificateDopInfoEntity(EditTechnicalCertificateDopInfoRequest technicalCertificateDopInfo) {
        return TechnicalCertificateDopInfo.builder()
                .brand(technicalCertificateDopInfo.getTechnical_certificate_dop_info().getBrand())
                .state_number(technicalCertificateDopInfo.getTechnical_certificate_dop_info().getState_number())
                .VIN_code(technicalCertificateDopInfo.getTechnical_certificate_dop_info().getVIN_code())
                .colour(technicalCertificateDopInfo.getTechnical_certificate_dop_info().getColour())
                .date_issue(technicalCertificateDopInfo.getTechnical_certificate_dop_info().getDate_issue())
                .seats(technicalCertificateDopInfo.getTechnical_certificate_dop_info().getSeats())
                .full_weight(technicalCertificateDopInfo.getTechnical_certificate_dop_info().getFull_weight())
                .empty_weight(technicalCertificateDopInfo.getTechnical_certificate_dop_info().getEmpty_weight())
                .category(technicalCertificateDopInfo.getTechnical_certificate_dop_info().getCategory())
                .fuel(technicalCertificateDopInfo.getTechnical_certificate_dop_info().getFuel())
                .body_type(technicalCertificateDopInfo.getTechnical_certificate_dop_info().getBody_type())
                .engine_volume(technicalCertificateDopInfo.getTechnical_certificate_dop_info().getEngine_volume())
                .engine_power(technicalCertificateDopInfo.getTechnical_certificate_dop_info().getEngine_power())
                .environmental_standard(technicalCertificateDopInfo.getTechnical_certificate_dop_info().getEnvironmental_standard())
                .build();
    }
}