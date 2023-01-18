package tech.na_app.services.transport;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import tech.na_app.converter.TransportConverter;
import tech.na_app.entity.transport.TransportCard;
import tech.na_app.entity.transport.*;
import tech.na_app.entity.user.User;
import tech.na_app.model.ApiException;
import tech.na_app.model.ErrorObject;
import tech.na_app.model.enums.Fuel;
import tech.na_app.model.transport.*;
import tech.na_app.model.transport.general_info.EditTransportGeneralInfoRequest;
import tech.na_app.model.transport.general_info.EditTransportGeneralInfoResponse;
import tech.na_app.model.transport.nomenclature_name.EditNomenclatureNameRequest;
import tech.na_app.model.transport.nomenclature_name.EditNomenclatureNameResponse;
import tech.na_app.model.transport.technical_certificate.EditTechnicalCertificateRequest;
import tech.na_app.model.transport.technical_certificate.EditTechnicalCertificateResponse;
import tech.na_app.model.transport.technical_certificate_dop_info.EditTechnicalCertificateDopInfoRequest;
import tech.na_app.model.transport.technical_certificate_dop_info.EditTechnicalCertificateDopInfoResponse;
import tech.na_app.model.transport.using_reason.EditTransportUsingReasonInfoRequest;
import tech.na_app.model.transport.using_reason.EditTransportUsingReasonInfoResponse;
import tech.na_app.repository.TransportRepository;
import tech.na_app.utils.SequenceGeneratorService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Log4j2
@Service
@RequiredArgsConstructor
public class TransportService {

    private final SequenceGeneratorService sequenceGeneratorService;
    private final TransportRepository transportRepository;
    private final TransportConverter transportConverter;

    public SaveNewTransportResponse saveNewTransport(String requestId, SaveNewTransportRequest request, User user) {
        try {
            if (Objects.isNull(request)) {
                throw new ApiException(400, "BAD_REQUEST");
            }
            TransportSequence sequenceNumber = (TransportSequence) sequenceGeneratorService.getSequenceNumber(Transport.SEQUENCE_NAME, TransportSequence.class);
            transportRepository.save(transportConverter.convertToTransportEntity(request, sequenceNumber, user));
            return new SaveNewTransportResponse(new ErrorObject(0));
        } catch (ApiException e) {
            log.error(requestId + " Error: " + e.getCode() + " Message: " + e.getMessage());
            return new SaveNewTransportResponse(new ErrorObject(e.getCode(), e.getMessage()));
        } catch (Exception e) {
            log.error(requestId + " Message: " + e.getMessage());
            return new SaveNewTransportResponse(new ErrorObject(500, "Something went wrong"));
        }
    }

    public GetAllTransportResponse getAllTransport(String requestId, User user) {
        try {
            List<Transport> allTransport = transportRepository.findAllByCompanyId(user.getCompanyId());
            if (allTransport.isEmpty()) {
                log.info(requestId + " Transport were not found");
                return new GetAllTransportResponse(new ErrorObject(0));
            }

            return GetAllTransportResponse.builder()
                    .transports(transportConverter.convertToTransports(allTransport))
                    .errorObject(new ErrorObject(0))
                    .build();
        } catch (Exception e) {
            log.error(requestId + " Error: " + 500 + " Message: " + e.getMessage());
            return new GetAllTransportResponse(new ErrorObject(500, e.getMessage()));
        }
    }

    public EditTransportGeneralInfoResponse editTransportGeneralInfo(String requestId, EditTransportGeneralInfoRequest request) {
        try {
            if (Objects.isNull(request)) {
                throw new ApiException(400, "BAD REQUEST");
            }
            if (Objects.isNull(request.getGeneral_info()) || Objects.isNull(request.getId())) {
                throw new ApiException(400, "BAD REQUEST");
            }

            Optional<Transport> transportOptional = transportRepository.findById(request.getId());
            Transport transport = transportOptional.orElseThrow(() -> new ApiException(404, "Not Found"));


            GeneralInfo buildGeneralInfo = GeneralInfo.builder()
                    .width(request.getGeneral_info().getWidth())
                    .height(request.getGeneral_info().getHeight())
                    .length(request.getGeneral_info().getLength())
                    .fuel_tank_volume(request.getGeneral_info().getFuel_tank_volume())
                    .mileage(request.getGeneral_info().getMileage())
                    .build();

            if (transport.getTransport_card() != null) {
                transport.getTransport_card().setGeneral_info(buildGeneralInfo);
            } else {
                TransportCard transport_card = TransportCard
                        .builder()
                        .general_info(buildGeneralInfo)
                        .build();
                transport.setTransport_card(transport_card);
            }
            transportRepository.save(transport);

            return new EditTransportGeneralInfoResponse(new ErrorObject(0));
        } catch (ApiException e) {
            log.error(requestId + " Error: " + e.getCode() + " Message: " + e.getMessage());
            return new EditTransportGeneralInfoResponse(new ErrorObject(e.getCode(), e.getMessage()));
        } catch (Exception e) {
            log.error(requestId + " Message: " + e.getMessage());
            return new EditTransportGeneralInfoResponse(new ErrorObject(500, "Something went wrong"));
        }
    }

    public EditTransportUsingReasonInfoResponse editTransportUsingReasonInfo(String requestId, EditTransportUsingReasonInfoRequest request) {
        try {
            if (Objects.isNull(request) || Objects.isNull(request.getId())) {
                throw new ApiException(400, "BAD REQUEST");
            }

            Optional<Transport> transportOptional = transportRepository.findById(request.getId());
            Transport transport = transportOptional.orElseThrow(() -> new ApiException(404, "Not Found"));


            UsingReasonInfo buildUsingReasonInfo = UsingReasonInfo.builder()
                    .num_and_name_contract(request.getUsing_reason_info().getNum_and_name_contract())
                    .date_start(request.getUsing_reason_info().getDate_start())
                    .is_contract_fixed_term(request.getUsing_reason_info().getIs_contract_fixed_term())
                    .date_end(request.getUsing_reason_info().getDate_end())
                    .date_next_start(request.getUsing_reason_info().getDate_next_start())
                    .build();

            if (transport.getTransport_card() != null) {
                transport.getTransport_card().setUsing_reason_info(buildUsingReasonInfo);
            } else {
                TransportCard transport_card = TransportCard
                        .builder()
                        .using_reason_info(buildUsingReasonInfo)
                        .build();
                transport.setTransport_card(transport_card);
            }
            transportRepository.save(transport);

            return new EditTransportUsingReasonInfoResponse(new ErrorObject(0));
        } catch (ApiException e) {
            log.error(requestId + " Error: " + e.getCode() + " Message: " + e.getMessage());
            return new EditTransportUsingReasonInfoResponse(new ErrorObject(e.getCode(), e.getMessage()));
        } catch (Exception e) {
            log.error(requestId + " Message: " + e.getMessage());
            return new EditTransportUsingReasonInfoResponse(new ErrorObject(500, "Something went wrong"));
        }
    }

    public GetTransportInfoResponse getTransportInfo(String requestId, User user, GetTransportInfoRequest request) {
        try {
            if (Objects.isNull(request) || Objects.isNull(request.getId()) || Objects.isNull(user.getCompanyId())) {
                throw new ApiException(400, "BAD REQUEST");
            }

            Optional<Transport> transportOptional = transportRepository.findByIdAndCompanyId(request.getId(), user.getCompanyId());
            Transport transport = transportOptional.orElseThrow(() -> new ApiException(400, "BAD REQUEST"));


            GetTransportInfoResponse response = transportConverter.convertToTransportModel(transport);
            response.setError(new ErrorObject(0));
            return response;
        } catch (ApiException e) {
            log.error(requestId + " Error: " + e.getCode() + " Message: " + e.getMessage());
            return new GetTransportInfoResponse(new ErrorObject(e.getCode(), e.getMessage()));
        } catch (Exception e) {
            log.error(requestId + " Message: " + e.getMessage());
            return new GetTransportInfoResponse(new ErrorObject(500, "Something went wrong"));
        }
    }


    public EditTechnicalCertificateResponse editTechnicalCertificate(String requestId, EditTechnicalCertificateRequest request) {
        try {
            if (Objects.isNull(request)) {
                throw new ApiException(400, "BAD REQUEST");
            }
            if (Objects.isNull(request.getTechnical_certificate()) || Objects.isNull(request.getId())) {
                throw new ApiException(400, "BAD REQUEST");
            }

            Transport transport = transportRepository.findById(request.getId())
                    .orElseThrow(() -> new ApiException(404, "Not Found"));

            TechnicalCertificate buildTechnicalCertificate = TechnicalCertificate.builder()
                    .num_and_series(request.getTechnical_certificate().getNum_and_series())
                    .issued_by(request.getTechnical_certificate().getIssued_by())
                    .date_end(request.getTechnical_certificate().getDate_end())
                    .date_issue(request.getTechnical_certificate().getDate_issue())
                    .build();

            if (transport.getTransport_card() != null) {
                transport.getTransport_card().setTechnical_certificate(buildTechnicalCertificate);
            } else {
                TransportCard transport_card = TransportCard
                        .builder()
                        .technical_certificate(buildTechnicalCertificate)
                        .build();
                transport.setTransport_card(transport_card);
            }
            transportRepository.save(transport);

            return new EditTechnicalCertificateResponse(new ErrorObject(0));
        } catch (ApiException e) {
            log.error(requestId + " Error: " + e.getCode() + " Message: " + e.getMessage());
            return new EditTechnicalCertificateResponse(new ErrorObject(e.getCode(), e.getMessage()));
        } catch (Exception e) {
            log.error(requestId + " Message: " + e.getMessage());
            return new EditTechnicalCertificateResponse(new ErrorObject(500, "Something went wrong"));
        }
    }

    public EditNomenclatureNameResponse editNomenclatureName(String requestId, EditNomenclatureNameRequest request) {
        try {
            if (Objects.isNull(request)) {
                throw new ApiException(400, "BAD REQUEST");
            }

            if (Objects.isNull(request.getNomenclature_name()) || Objects.isNull(request.getId())) {
                throw new ApiException(400, "BAD REQUEST");
            }

            Transport transport = transportRepository.findById(request.getId())
                    .orElseThrow(() -> new ApiException(404, "Not Found"));


            if (transport.getTransport_card() != null) {
                transport.getTransport_card().setNomenclature_name(request.getNomenclature_name());
            } else {
                TransportCard transport_card = TransportCard
                        .builder()
                        .nomenclature_name(request.getNomenclature_name())
                        .build();
                transport.setTransport_card(transport_card);
            }
            transportRepository.save(transport);

            return new EditNomenclatureNameResponse(new ErrorObject(0));
        } catch (ApiException e) {
            log.error(requestId + " Error: " + e.getCode() + " Message: " + e.getMessage());
            return new EditNomenclatureNameResponse(new ErrorObject(e.getCode(), e.getMessage()));
        } catch (Exception e) {
            log.error(requestId + " Message: " + e.getMessage());
            return new EditNomenclatureNameResponse(new ErrorObject(500, "Something went wrong"));
        }
    }

    public EditTechnicalCertificateDopInfoResponse editTechnicalCertificateDopInfo(String requestId, EditTechnicalCertificateDopInfoRequest request) {
        try {
            if (Objects.isNull(request)) {
                throw new ApiException(400, "BAD REQUEST");
            }

            if (Objects.isNull(request.getTechnical_certificate_dop_info()) || Objects.isNull(request.getId())) {
                throw new ApiException(400, "BAD REQUEST");
            }

            Transport transport = transportRepository.findById(request.getId())
                    .orElseThrow(() -> new ApiException(404, "Not Found"));

            TechnicalCertificateDopInfo buildTechnicalCertificateDopInfo = transportConverter.convertToTechnicalCertificateDopInfoEntity(request);

            if (transport.getTransport_card() != null && transport.getTransport_card().getTechnical_certificate() != null) {
                transport.getTransport_card().getTechnical_certificate().setTechnical_certificate_dop_info(buildTechnicalCertificateDopInfo);
            } else {
                TransportCard transport_card = TransportCard
                        .builder()
                        .technical_certificate(TechnicalCertificate.builder()
                                .technical_certificate_dop_info(buildTechnicalCertificateDopInfo)
                                .build())
                        .build();
                transport.setTransport_card(transport_card);
            }
            transportRepository.save(transport);

            return new EditTechnicalCertificateDopInfoResponse(new ErrorObject(0));
        } catch (ApiException e) {
            log.error(requestId + " Error: " + e.getCode() + " Message: " + e.getMessage());
            return new EditTechnicalCertificateDopInfoResponse(new ErrorObject(e.getCode(), e.getMessage()));
        } catch (Exception e) {
            log.error(requestId + " Message: " + e.getMessage());
            return new EditTechnicalCertificateDopInfoResponse(new ErrorObject(500, "Something went wrong"));
        }
    }

    public GetFuelResponse getFuel(String requestId) {
        try {

            List<GetFuelResponse.GetFuel> fuels = new ArrayList<>();

            for (var value : Fuel.values()) {
                fuels.add(
                        GetFuelResponse.GetFuel.builder()
                                .fuel(value)
                                .name(value.getValue())
                                .build()
                );
            }

            return GetFuelResponse.builder()
                    .fuels(fuels)
                    .build();
        } catch (ApiException e) {
            log.error(requestId + " Error: " + e.getCode() + " Message: " + e.getMessage());
            return new GetFuelResponse(new ErrorObject(e.getCode(), e.getMessage()));
        } catch (Exception e) {
            log.error(requestId + " Message: " + e.getMessage());
            return new GetFuelResponse(new ErrorObject(500, "Something went wrong"));
        }
    }

}
