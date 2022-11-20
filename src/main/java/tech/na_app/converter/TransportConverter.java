package tech.na_app.converter;

import org.springframework.stereotype.Component;
import tech.na_app.entity.transport.*;
import tech.na_app.model.transport.SaveNewTransportRequest;

import java.util.Date;
import java.util.Objects;

@Component
public class TransportConverter {

    public Transport convertToTransportEntity(SaveNewTransportRequest request, TransportSequence transportSequence) {
        return Transport.builder()
                .id(transportSequence.getSeq())
                .create_date(new Date())
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
                .company_id(transportSequence.getSeq())
                .build();
    }
}