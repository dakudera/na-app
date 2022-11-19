package tech.na_app.services.transport;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import tech.na_app.entity.company.*;
import tech.na_app.entity.transport.Transport;
import tech.na_app.entity.transport.TransportSequence;
import tech.na_app.model.ApiException;
import tech.na_app.model.ErrorObject;
import tech.na_app.model.transport.SaveNewTransportRequest;
import tech.na_app.model.transport.SaveNewTransportResponse;
import tech.na_app.repository.TransportRepository;
import tech.na_app.utils.SequenceGeneratorService;

import java.util.Date;
import java.util.Objects;

@Log4j2
@Service
@RequiredArgsConstructor
public class TransportService {

    private final SequenceGeneratorService sequenceGeneratorService;
    private final TransportRepository transportRepository;

    public SaveNewTransportResponse saveNewTransport(String requestId, SaveNewTransportRequest request) {
        try {
            if (Objects.isNull(request)) {
                throw new ApiException(400, "BAD_REQUEST");
            }

            TransportSequence sequenceNumber = (TransportSequence) sequenceGeneratorService.getSequenceNumber(Transport.SEQUENCE_NAME, TransportSequence.class);

            transportRepository.save(
                    Transport.builder()
                            .id(sequenceNumber.getSeq())
                            .create_date(new Date())
                            .ukr_name(
                                    request.getUkr_name() != null ?
                                            CompanyName.builder()
                                                    .full_name(request.getUkr_name().getFull_name())
                                                    .short_name(request.getUkr_name().getShort_name())
                                                    .build() : null
                            )
                            .eng_name(
                                    request.getEng_name() != null ?
                                            CompanyName.builder()
                                                    .full_name(request.getEng_name().getFull_name())
                                                    .short_name(request.getEng_name().getShort_name())
                                                    .build() : null
                            )
                            .address(request.getAddress())
                            .postal_address(request.getPostal_address())
                            .communication(
                                    request.getCommunication() != null ?
                                            Communication.builder()
                                                    .email(request.getCommunication().getEmail())
                                                    .phone_number(request.getCommunication().getPhone_number())
                                                    .build() : null
                            )
                            .banking_details(
                                    request.getBanking_details() != null ?
                                            BankingDetails.builder()
                                                    .iban(request.getBanking_details().getIban())
                                                    .remittance_bank(request.getBanking_details().getRemittance_bank())
                                                    .build() : null
                            )
                            .identification_details(
                                    request.getIdentification_details() != null ?
                                            IdentificationDetails.builder()
                                                    .edrpou(request.getIdentification_details().getEdrpou())
                                                    .registration_certificate(request.getIdentification_details().getRegistration_certificate())
                                                    .ipn(request.getIdentification_details().getIpn())
                                                    .accounting_tax_info(request.getIdentification_details().getAccounting_tax_info())
                                                    .tax_form(request.getIdentification_details().getTax_form())
                                                    .build() : null
                            )
                            .licence_info(request.getLicence_info())
                            .build()
            );

            return new SaveNewTransportResponse(new ErrorObject(0));
        } catch (ApiException e) {
            log.error(requestId + " Error: " + e.getCode() + " Message: " + e.getMessage());
            return new SaveNewTransportResponse(new ErrorObject(e.getCode(), e.getMessage()));
        }
    }

}
