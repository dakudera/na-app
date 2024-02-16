package tech.na_app.services.transport.get_data;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import tech.na_app.converter.TransportConverter;
import tech.na_app.entity.transport.TransportCard;
import tech.na_app.entity.transport.*;
import tech.na_app.entity.user.User;
import tech.na_app.model.ApiException;
import tech.na_app.model.ErrorObject;
import tech.na_app.model.enums.EnvironmentalStandard;
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
import tech.na_app.services.transport.get_data.GetEnvironmentalStandardService;
import tech.na_app.utils.SequenceGeneratorService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Log4j2
@Service
public class GetEnvironmentalStandardServiceImpl implements GetEnvironmentalStandardService {


    public GetEnvironmentalStandardResponse getEnvironmentalStandard(String requestId) {
        try {

            List<GetEnvironmentalStandardResponse.GetEnvironmentalStandard> standards = new ArrayList<>();

            for (var value : EnvironmentalStandard.values()) {
                standards.add(
                        GetEnvironmentalStandardResponse.GetEnvironmentalStandard
                                .builder()
                                .standard(value)
                                .name(value.getValue())
                                .build()
                );
            }

            return GetEnvironmentalStandardResponse.builder()
                    .standards(standards)
                    .build();
        } catch (ApiException e) {
            log.error(requestId + " Error: " + e.getCode() + " Message: " + e.getMessage());
            return new GetEnvironmentalStandardResponse(new ErrorObject(e.getCode(), e.getMessage()));
        } catch (Exception e) {
            log.error(requestId + " Message: " + e.getMessage());
            return new GetEnvironmentalStandardResponse(new ErrorObject(500, "Something went wrong"));
        }
    }

}
