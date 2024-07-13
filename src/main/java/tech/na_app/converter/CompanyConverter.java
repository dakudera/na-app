package tech.na_app.converter;

import org.springframework.stereotype.Component;
import tech.na_app.entity.company.*;
import tech.na_app.model.company.GetCompanyInfoResponse;
import tech.na_app.model.company.SaveNewCompanyRequest;
import tech.na_app.model.exceptions.ErrorObject;

import java.util.Date;

@Component
public class CompanyConverter {

    public Company convertToCompanyEntity(SaveNewCompanyRequest request) {
        CompanyName ukrainianCompanyName = request.ukr_name() != null ?
                CompanyName.builder()
                        .full_name(request.ukr_name().getFull_name())
                        .short_name(request.ukr_name().getShort_name())
                        .build() : null;
        CompanyName englishCompanyName = request.eng_name() != null ?
                CompanyName.builder()
                        .full_name(request.eng_name().getFull_name())
                        .short_name(request.eng_name().getShort_name())
                        .build() : null;
        Communication communication = request.communication() != null ?
                Communication.builder()
                        .email(request.communication().getEmail())
                        .phone_number(request.communication().getPhone_number())
                        .build() : null;
        BankingDetails bankingDetails = request.banking_details() != null ?
                BankingDetails.builder()
                        .iban(request.banking_details().getIban())
                        .remittance_bank(request.banking_details().getRemittance_bank())
                        .build() : null;
        IdentificationDetails identificationDetails = request.identification_details() != null ?
                IdentificationDetails.builder()
                        .edrpou(request.identification_details().getEdrpou())
                        .registration_certificate(request.identification_details().getRegistration_certificate())
                        .ipn(request.identification_details().getIpn())
                        .accounting_tax_info(request.identification_details().getAccounting_tax_info())
                        .tax_form(request.identification_details().getTax_form())
                        .build() : null;
        return Company.builder()
                .create_date(new Date())
                .ukr_name(ukrainianCompanyName)
                .eng_name(englishCompanyName)
                .address(request.address())
                .postal_address(request.postal_address())
                .communication(communication)
                .banking_details(bankingDetails)
                .identification_details(identificationDetails)
                .licence_info(request.licence_info())
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
