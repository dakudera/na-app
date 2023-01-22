package tech.na_app.services.company;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tech.na_app.converter.CompanyConverter;
import tech.na_app.entity.company.BankingDetails;
import tech.na_app.entity.company.Company;
import tech.na_app.entity.user.User;
import tech.na_app.model.ErrorObject;
import tech.na_app.model.company.company_global_info.Communication;
import tech.na_app.model.company.company_global_info.EditCompanyGlobalInfoRequest;
import tech.na_app.model.company.company_global_info.EditCompanyGlobalInfoResponse;
import tech.na_app.repository.CompanyRepository;
import tech.na_app.utils.SequenceGeneratorService;
import tech.na_app.utils.TestUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.lenient;

@ExtendWith({MockitoExtension.class})
class CompanyServiceTest {

    @Mock
    private CompanyRepository companyRepository;

    @Mock
    private SequenceGeneratorService sequenceGeneratorService;

    @Mock
    private CompanyConverter companyConverter;

    private CompanyService companyService;

    @BeforeEach
    public void setUp() {
        companyService = new CompanyService(companyRepository, sequenceGeneratorService, companyConverter);
    }

    @ParameterizedTest
    @MethodSource("editCompanyGlobalInfo$BadDataSet")
    void editCompanyGlobalInfo$BadRequest(EditCompanyGlobalInfoRequest request, EditCompanyGlobalInfoResponse expectedResponse,
                                          Optional<Company> mockedCompany) {
        User user = User.builder().id(1).companyId(1).build();

        lenient().when(companyRepository.findById(user.getId())).thenReturn(mockedCompany);

        EditCompanyGlobalInfoResponse response = companyService.editCompanyGlobalInfo(TestUtils.TEST_REQUEST_ID, user, request);

        assertEquals(expectedResponse, response);
    }

    private static Stream<Arguments> editCompanyGlobalInfo$BadDataSet() {
        return Stream.of(
                //-1-// address is NULL
                Arguments.of(
                        EditCompanyGlobalInfoRequest.builder()
                                .address(null)
                                .postal_address("ylica pupi i lupi")
                                .communication(Communication.builder()
                                        .email("pupailupa@Ggmail.com")
                                        .phone_number(List.of("0662744321", "0662744123"))
                                        .build())
                                .banking_details(tech.na_app.model.company.company_global_info.BankingDetails.builder()
                                        .iban("UA793073500000037398800001488")
                                        .remittance_bank("LOL")
                                        .build())
                                .build(),

                        EditCompanyGlobalInfoResponse.builder()
                                .error(new ErrorObject(400, "BAD REQUEST"))
                                .build(),

                        Optional.of(Company.builder()
                                .id(1)
                                .address("ulica charkivska")
                                .postal_address("gdeto tam")
                                .banking_details(BankingDetails.builder()
                                        .iban("UA793073500000037398800001488")
                                        .remittance_bank("LOL")
                                        .build())
                                .communication(tech.na_app.entity.company.Communication.builder()
                                        .email("lilchicha@gmail.com")
                                        .phone_number(List.of("0662744321", "0662744123"))
                                        .build())
                                .build())
                ),
                //-2-// postal_address is NULL
                Arguments.of(
                        EditCompanyGlobalInfoRequest.builder()
                                .address("ulica lamana")
                                .postal_address(null)
                                .communication(Communication.builder()
                                        .email("pupailupa@Ggmail.com")
                                        .phone_number(List.of("0662744321", "0662744123"))
                                        .build())
                                .banking_details(tech.na_app.model.company.company_global_info.BankingDetails.builder()
                                        .iban("UA793073500000037398800001488")
                                        .remittance_bank("LOL")
                                        .build())
                                .build(),

                        EditCompanyGlobalInfoResponse.builder()
                                .error(new ErrorObject(400, "BAD REQUEST"))
                                .build(),

                        Optional.of(Company.builder()
                                .id(1)
                                .address("ulica charkivska")
                                .postal_address("gdeto tam")
                                .banking_details(BankingDetails.builder()
                                        .iban("UA793073500000037398800001488")
                                        .remittance_bank("LOL")
                                        .build())
                                .communication(tech.na_app.entity.company.Communication.builder()
                                        .email("lilchicha@gmail.com")
                                        .phone_number(List.of("0662744321", "0662744123"))
                                        .build())
                                .build())
                ),
                //-3-// communication is NULL
                Arguments.of(
                        EditCompanyGlobalInfoRequest.builder()
                                .address("ulica lamana")
                                .postal_address("ylica pupi i lupi")
                                .communication(null)
                                .banking_details(tech.na_app.model.company.company_global_info.BankingDetails.builder()
                                        .iban("UA793073500000037398800001488")
                                        .remittance_bank("LOL")
                                        .build())
                                .build(),

                        EditCompanyGlobalInfoResponse.builder()
                                .error(new ErrorObject(400, "BAD REQUEST"))
                                .build(),

                        Optional.of(Company.builder()
                                .id(1)
                                .address("ulica charkivska")
                                .postal_address("gdeto tam")
                                .banking_details(BankingDetails.builder()
                                        .iban("UA793073500000037398800001488")
                                        .remittance_bank("LOL")
                                        .build())
                                .communication(tech.na_app.entity.company.Communication.builder()
                                        .email("lilchicha@gmail.com")
                                        .phone_number(List.of("0662744321", "0662744123"))
                                        .build())
                                .build())
                ),
                //-4-// banking_details is NULL
                Arguments.of(
                        EditCompanyGlobalInfoRequest.builder()
                                .address("ulica lamana")
                                .postal_address("ylica pupi i lupi")
                                .communication(Communication.builder()
                                        .email("pupailupa@Ggmail.com")
                                        .phone_number(List.of("0662744321", "0662744123"))
                                        .build())
                                .banking_details(null)
                                .build(),

                        EditCompanyGlobalInfoResponse.builder()
                                .error(new ErrorObject(400, "BAD REQUEST"))
                                .build(),

                        Optional.of(Company.builder()
                                .id(1)
                                .address("ulica charkivska")
                                .postal_address("gdeto tam")
                                .banking_details(BankingDetails.builder()
                                        .iban("UA793073500000037398800001488")
                                        .remittance_bank("LOL")
                                        .build())
                                .communication(tech.na_app.entity.company.Communication.builder()
                                        .email("lilchicha@gmail.com")
                                        .phone_number(List.of("0662744321", "0662744123"))
                                        .build())
                                .build())
                ),
                //-5-// Not found company
                Arguments.of(
                        EditCompanyGlobalInfoRequest.builder()
                                .address("ulica lamana")
                                .postal_address("ylica pupi i lupi")
                                .communication(Communication.builder()
                                        .email("pupailupa@Ggmail.com")
                                        .phone_number(List.of("0662744321", "0662744123"))
                                        .build())
                                .banking_details(tech.na_app.model.company.company_global_info.BankingDetails.builder()
                                        .iban("UA793073500000037398800001488")
                                        .remittance_bank("LOL")
                                        .build())
                                .build(),

                        EditCompanyGlobalInfoResponse.builder()
                                .error(new ErrorObject(404, "Not Found"))
                                .build(),

                        Optional.empty()
                ),
                //-6-// request is NULL
                Arguments.of(
                        null,

                        EditCompanyGlobalInfoResponse.builder()
                                .error(new ErrorObject(400, "BAD REQUEST"))
                                .build(),

                        Optional.of(Company.builder()
                                .id(1)
                                .address("ulica charkivska")
                                .postal_address("gdeto tam")
                                .banking_details(BankingDetails.builder()
                                        .iban("UA793073500000037398800001488")
                                        .remittance_bank("LOL")
                                        .build())
                                .communication(tech.na_app.entity.company.Communication.builder()
                                        .email("lilchicha@gmail.com")
                                        .phone_number(List.of("0662744321", "0662744123"))
                                        .build())
                                .build())
                )
        );
    }

    @ParameterizedTest
    @MethodSource("editCompanyGlobalInfo$GoodDataSet")
    void editCompanyGlobalInfo$GoodRequest(EditCompanyGlobalInfoRequest request, EditCompanyGlobalInfoResponse expectedResponse,
                                           Optional<Company> mockedCompany) {
        User user = User.builder().id(1).companyId(1).build();

        lenient().when(companyRepository.findById(user.getId())).thenReturn(mockedCompany);

        EditCompanyGlobalInfoResponse response = companyService.editCompanyGlobalInfo(TestUtils.TEST_REQUEST_ID, user, request);

        assertEquals(expectedResponse, response);
    }

    private static Stream<Arguments> editCompanyGlobalInfo$GoodDataSet() {
        return Stream.of(
                //-1-//
                Arguments.of(
                        EditCompanyGlobalInfoRequest.builder()
                                .address("panikaxi")
                                .postal_address("ylica pupi i lupi")
                                .communication(Communication.builder()
                                        .email("pupailupa@Ggmail.com")
                                        .phone_number(List.of("0662744321", "0662744123"))
                                        .build())
                                .banking_details(tech.na_app.model.company.company_global_info.BankingDetails.builder()
                                        .iban("UA793073500000037398800001488")
                                        .remittance_bank("LOL")
                                        .build())
                                .build(),

                        EditCompanyGlobalInfoResponse.builder()
                                .error(new ErrorObject(0))
                                .build(),

                        Optional.of(Company.builder()
                                .id(1)
                                .address("ulica charkivska")
                                .postal_address("gdeto tam")
                                .banking_details(BankingDetails.builder()
                                        .iban("UA793073500000037398800001488")
                                        .remittance_bank("LOL")
                                        .build())
                                .communication(tech.na_app.entity.company.Communication.builder()
                                        .email("lilchicha@gmail.com")
                                        .phone_number(List.of("0662744321", "0662744123"))
                                        .build())
                                .build())
                ),
                //-2-// Company banking_details is NULL in DB
                Arguments.of(
                        EditCompanyGlobalInfoRequest.builder()
                                .address("ulica lamana")
                                .postal_address("ylica pupi i lupi")
                                .communication(Communication.builder()
                                        .email("pupailupa@Ggmail.com")
                                        .phone_number(List.of("0662744321", "0662744123"))
                                        .build())
                                .banking_details(tech.na_app.model.company.company_global_info.BankingDetails.builder()
                                        .iban("UA793073500000037398800001488")
                                        .remittance_bank("LOL")
                                        .build())
                                .build(),

                        EditCompanyGlobalInfoResponse.builder()
                                .error(new ErrorObject(0))
                                .build(),

                        Optional.of(Company.builder()
                                .id(1)
                                .address("ulica charkivska")
                                .postal_address("gdeto tam")
                                .banking_details(null)
                                .communication(tech.na_app.entity.company.Communication.builder()
                                        .email("lilchicha@gmail.com")
                                        .phone_number(List.of("0662744321", "0662744123"))
                                        .build())
                                .build())
                ),
                //-3-// Company communication is NULL in DB
                Arguments.of(
                        EditCompanyGlobalInfoRequest.builder()
                                .address("ulica lamana")
                                .postal_address("ylica pupi i lupi")
                                .communication(Communication.builder()
                                        .email("pupailupa@Ggmail.com")
                                        .phone_number(List.of("0662744321", "0662744123"))
                                        .build())
                                .banking_details(tech.na_app.model.company.company_global_info.BankingDetails.builder()
                                        .iban("UA793073500000037398800001488")
                                        .remittance_bank("LOL")
                                        .build())
                                .build(),

                        EditCompanyGlobalInfoResponse.builder()
                                .error(new ErrorObject(0))
                                .build(),

                        Optional.of(Company.builder()
                                .id(1)
                                .address("ulica charkivska")
                                .postal_address("gdeto tam")
                                .banking_details(BankingDetails.builder()
                                        .iban("UA793073500000037398800001488")
                                        .remittance_bank("LOL")
                                        .build())
                                .communication(null)
                                .build())
                )
        );
    }
}