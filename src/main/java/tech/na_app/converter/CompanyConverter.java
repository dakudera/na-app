package tech.na_app.converter;

import org.springframework.stereotype.Component;
import tech.na_app.entity.company.*;
import tech.na_app.model.exceptions.ErrorObject;
import tech.na_app.model.company.GetCompanyInfoResponse;
import tech.na_app.model.company.SaveNewCompanyRequest;

import java.util.Date;

@Component
public class CompanyConverter {

    public Company convertToCompanyEntity(SaveNewCompanyRequest request, CompanySequence companySequence) {
        return Company.builder()
                .id(companySequence.getSeq())
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
                .build();
    }

    public GetCompanyInfoResponse convertToGetCompanyInfoResponse(Company company) {
        return GetCompanyInfoResponse.builder()
                .ukrName(
                        company.getUkr_name() != null ?
                                tech.na_app.model.company.conpany_name.CompanyName.builder()
                                        .full_name(company.getUkr_name().getFull_name())
                                        .short_name(company.getUkr_name().getShort_name())
                                        .build() : null
                )
                .engName(
                        company.getEng_name() != null ?
                                tech.na_app.model.company.conpany_name.CompanyName.builder()
                                        .full_name(company.getEng_name().getFull_name())
                                        .short_name(company.getEng_name().getShort_name())
                                        .build() : null
                )
                .address(company.getAddress())
                .postalAddress(company.getPostal_address())
                .communication(
                        company.getCommunication() != null ?
                                tech.na_app.model.company.company_global_info.Communication.builder()
                                        .email(company.getCommunication().getEmail())
                                        .phone_number(company.getCommunication().getPhone_number())
                                        .build() : null
                )
                .bankingDetails(
                        company.getBanking_details() != null ?
                                tech.na_app.model.company.company_global_info.BankingDetails.builder()
                                        .iban(company.getBanking_details().getIban())
                                        .remittance_bank(company.getBanking_details().getRemittance_bank())
                                        .build() : null
                )
                .identificationDetails(
                        company.getIdentification_details() != null ?
                                tech.na_app.model.company.identification_detalis.IdentificationDetails.builder()
                                        .edrpou(company.getIdentification_details().getEdrpou())
                                        .registration_certificate(company.getIdentification_details().getRegistration_certificate())
                                        .ipn(company.getIdentification_details().getIpn())
                                        .accounting_tax_info(company.getIdentification_details().getAccounting_tax_info())
                                        .tax_form(company.getIdentification_details().getTax_form())
                                        .build() : null
                )
                .licenceInfo(company.getLicence_info())
                .error(new ErrorObject(0))
                .build();

    }

}
