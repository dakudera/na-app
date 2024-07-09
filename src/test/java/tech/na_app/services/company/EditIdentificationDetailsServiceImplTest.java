//package tech.na_app.services.company;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.provider.Arguments;
//import org.junit.jupiter.params.provider.MethodSource;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import tech.na_app.converter.CompanyConverter;
//import tech.na_app.entity.company.BankingDetails;
//import tech.na_app.entity.company.Company;
//import tech.na_app.entity.user.User;
//import tech.na_app.model.exceptions.ErrorObject;
//import tech.na_app.model.company.company_global_info.Communication;
//import tech.na_app.model.company.company_global_info.EditCompanyGlobalInfoRequest;
//import tech.na_app.model.company.company_global_info.EditCompanyGlobalInfoResponse;
//import tech.na_app.model.company.identification_detalis.EditIdentificationDetailsRequest;
//import tech.na_app.model.company.identification_detalis.EditIdentificationDetailsResponse;
//import tech.na_app.model.company.identification_detalis.IdentificationDetails;
//import tech.na_app.repository.CompanyRepository;
//import tech.na_app.services.company.edit_data.EditCompanyGlobalInfoServiceImpl;
//import tech.na_app.utils.SequenceGeneratorService;
//import tech.na_app.utils.TestUtils;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Stream;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.lenient;
//
//@ExtendWith({MockitoExtension.class})
//class EditIdentificationDetailsServiceImplTest {
//
//    @Mock
//    private CompanyRepository companyRepository;
//
//    @Mock
//    private SequenceGeneratorService sequenceGeneratorService;
//
//    @Mock
//    private CompanyConverter companyConverter;
//
//    private EditCompanyGlobalInfoServiceImpl editIdentificationDetailsServiceImpl;
//
//    @BeforeEach
//    public void setUp() {
//        editIdentificationDetailsServiceImpl = new EditCompanyGlobalInfoServiceImpl(companyRepository);
//    }
//
//    @ParameterizedTest
//    @MethodSource("editCompanyGlobalInfo$BadDataSet")
//    void editCompanyGlobalInfo$BadRequest(EditCompanyGlobalInfoRequest request, EditCompanyGlobalInfoResponse expectedResponse,
//                                          Optional<Company> mockedCompany) {
//        User user = User.builder().id(1).companyId(1).build();
//
//        lenient().when(companyRepository.findById(user.getId())).thenReturn(mockedCompany);
//
//        EditCompanyGlobalInfoResponse response = editIdentificationDetailsServiceImpl.editCompanyGlobalInfo(TestUtils.TEST_REQUEST_ID, user, request);
//
//        assertEquals(expectedResponse, response);
//    }
//
//    private static Stream<Arguments> editCompanyGlobalInfo$BadDataSet() {
//        return Stream.of(
//                //-1-// address is NULL
//                Arguments.of(
//                        EditCompanyGlobalInfoRequest.builder()
//                                .address(null)
//                                .postal_address("ylica pupi i lupi")
//                                .communication(Communication.builder()
//                                        .email("pupailupa@gmail.com")
//                                        .phone_number(List.of("0662744321", "0662744123"))
//                                        .build())
//                                .banking_details(tech.na_app.model.company.company_global_info.BankingDetails.builder()
//                                        .iban("UA793073500000037398800001488")
//                                        .remittance_bank("LOL")
//                                        .build())
//                                .build(),
//
//                        EditCompanyGlobalInfoResponse.builder()
//                                .error(new ErrorObject(400, "BAD REQUEST"))
//                                .build(),
//
//                        Optional.of(Company.builder()
//                                .id(1)
//                                .address("ulica charkivska")
//                                .postal_address("gdeto tam")
//                                .banking_details(BankingDetails.builder()
//                                        .iban("UA793073500000037398800001488")
//                                        .remittance_bank("LOL")
//                                        .build())
//                                .communication(tech.na_app.entity.company.Communication.builder()
//                                        .email("lilchicha@gmail.com")
//                                        .phone_number(List.of("0662744321", "0662744123"))
//                                        .build())
//                                .build())
//                ),
//                //-2-// postal_address is NULL
//                Arguments.of(
//                        EditCompanyGlobalInfoRequest.builder()
//                                .address("ulica lamana")
//                                .postal_address(null)
//                                .communication(Communication.builder()
//                                        .email("pupailupa@gmail.com")
//                                        .phone_number(List.of("0662744321", "0662744123"))
//                                        .build())
//                                .banking_details(tech.na_app.model.company.company_global_info.BankingDetails.builder()
//                                        .iban("UA793073500000037398800001488")
//                                        .remittance_bank("LOL")
//                                        .build())
//                                .build(),
//
//                        EditCompanyGlobalInfoResponse.builder()
//                                .error(new ErrorObject(400, "BAD REQUEST"))
//                                .build(),
//
//                        Optional.of(Company.builder()
//                                .id(1)
//                                .address("ulica charkivska")
//                                .postal_address("gdeto tam")
//                                .banking_details(BankingDetails.builder()
//                                        .iban("UA793073500000037398800001488")
//                                        .remittance_bank("LOL")
//                                        .build())
//                                .communication(tech.na_app.entity.company.Communication.builder()
//                                        .email("lilchicha@gmail.com")
//                                        .phone_number(List.of("0662744321", "0662744123"))
//                                        .build())
//                                .build())
//                ),
//                //-3-// communication is NULL
//                Arguments.of(
//                        EditCompanyGlobalInfoRequest.builder()
//                                .address("ulica lamana")
//                                .postal_address("ylica pupi i lupi")
//                                .communication(null)
//                                .banking_details(tech.na_app.model.company.company_global_info.BankingDetails.builder()
//                                        .iban("UA793073500000037398800001488")
//                                        .remittance_bank("LOL")
//                                        .build())
//                                .build(),
//
//                        EditCompanyGlobalInfoResponse.builder()
//                                .error(new ErrorObject(400, "BAD REQUEST"))
//                                .build(),
//
//                        Optional.of(Company.builder()
//                                .id(1)
//                                .address("ulica charkivska")
//                                .postal_address("gdeto tam")
//                                .banking_details(BankingDetails.builder()
//                                        .iban("UA793073500000037398800001488")
//                                        .remittance_bank("LOL")
//                                        .build())
//                                .communication(tech.na_app.entity.company.Communication.builder()
//                                        .email("lilchicha@gmail.com")
//                                        .phone_number(List.of("0662744321", "0662744123"))
//                                        .build())
//                                .build())
//                ),
//                //-4-// banking_details is NULL
//                Arguments.of(
//                        EditCompanyGlobalInfoRequest.builder()
//                                .address("ulica lamana")
//                                .postal_address("ylica pupi i lupi")
//                                .communication(Communication.builder()
//                                        .email("pupailupa@gmail.com")
//                                        .phone_number(List.of("0662744321", "0662744123"))
//                                        .build())
//                                .banking_details(null)
//                                .build(),
//
//                        EditCompanyGlobalInfoResponse.builder()
//                                .error(new ErrorObject(400, "BAD REQUEST"))
//                                .build(),
//
//                        Optional.of(Company.builder()
//                                .id(1)
//                                .address("ulica charkivska")
//                                .postal_address("gdeto tam")
//                                .banking_details(BankingDetails.builder()
//                                        .iban("UA793073500000037398800001488")
//                                        .remittance_bank("LOL")
//                                        .build())
//                                .communication(tech.na_app.entity.company.Communication.builder()
//                                        .email("lilchicha@gmail.com")
//                                        .phone_number(List.of("0662744321", "0662744123"))
//                                        .build())
//                                .build())
//                ),
//                //-5-// Not found company
//                Arguments.of(
//                        EditCompanyGlobalInfoRequest.builder()
//                                .address("ulica lamana")
//                                .postal_address("ylica pupi i lupi")
//                                .communication(Communication.builder()
//                                        .email("pupailupa@gmail.com")
//                                        .phone_number(List.of("0662744321", "0662744123"))
//                                        .build())
//                                .banking_details(tech.na_app.model.company.company_global_info.BankingDetails.builder()
//                                        .iban("UA793073500000037398800001488")
//                                        .remittance_bank("LOL")
//                                        .build())
//                                .build(),
//
//                        EditCompanyGlobalInfoResponse.builder()
//                                .error(new ErrorObject(404, "Not Found"))
//                                .build(),
//
//                        Optional.empty()
//                ),
//                //-6-// request is NULL
//                Arguments.of(
//                        null,
//
//                        EditCompanyGlobalInfoResponse.builder()
//                                .error(new ErrorObject(400, "BAD REQUEST"))
//                                .build(),
//
//                        Optional.of(Company.builder()
//                                .id(1)
//                                .address("ulica charkivska")
//                                .postal_address("gdeto tam")
//                                .banking_details(BankingDetails.builder()
//                                        .iban("UA793073500000037398800001488")
//                                        .remittance_bank("LOL")
//                                        .build())
//                                .communication(tech.na_app.entity.company.Communication.builder()
//                                        .email("lilchicha@gmail.com")
//                                        .phone_number(List.of("0662744321", "0662744123"))
//                                        .build())
//                                .build())
//                )
//        );
//    }
//
//    @ParameterizedTest
//    @MethodSource("editCompanyGlobalInfo$GoodDataSet")
//    void editCompanyGlobalInfo$GoodRequest(EditCompanyGlobalInfoRequest request, EditCompanyGlobalInfoResponse expectedResponse,
//                                           Optional<Company> mockedCompany) {
//        User user = User.builder().id(1).companyId(1).build();
//
//        lenient().when(companyRepository.findById(user.getId())).thenReturn(mockedCompany);
//
//        EditCompanyGlobalInfoResponse response = editIdentificationDetailsServiceImpl.editCompanyGlobalInfo(TestUtils.TEST_REQUEST_ID, user, request);
//
//        assertEquals(expectedResponse, response);
//    }
//
//    private static Stream<Arguments> editCompanyGlobalInfo$GoodDataSet() {
//        return Stream.of(
//                //-1-//
//                Arguments.of(
//                        EditCompanyGlobalInfoRequest.builder()
//                                .address("panikaxi")
//                                .postal_address("ylica pupi i lupi")
//                                .communication(Communication.builder()
//                                        .email("pupailupa@ggmail.com")
//                                        .phone_number(List.of("0662744321", "0662744123"))
//                                        .build())
//                                .banking_details(tech.na_app.model.company.company_global_info.BankingDetails.builder()
//                                        .iban("UA793073500000037398800001488")
//                                        .remittance_bank("LOL")
//                                        .build())
//                                .build(),
//
//                        EditCompanyGlobalInfoResponse.builder()
//                                .error(new ErrorObject(0))
//                                .build(),
//
//                        Optional.of(Company.builder()
//                                .id(1)
//                                .address("ulica charkivska")
//                                .postal_address("gdeto tam")
//                                .banking_details(BankingDetails.builder()
//                                        .iban("UA793073500000037398800001488")
//                                        .remittance_bank("LOL")
//                                        .build())
//                                .communication(tech.na_app.entity.company.Communication.builder()
//                                        .email("lilchicha@gmail.com")
//                                        .phone_number(List.of("0662744321", "0662744123"))
//                                        .build())
//                                .build())
//                ),
//                //-2-// Company banking_details is NULL in DB
//                Arguments.of(
//                        EditCompanyGlobalInfoRequest.builder()
//                                .address("ulica lamana")
//                                .postal_address("ylica pupi i lupi")
//                                .communication(Communication.builder()
//                                        .email("pupailupa@gmail.com")
//                                        .phone_number(List.of("0662744321", "0662744123"))
//                                        .build())
//                                .banking_details(tech.na_app.model.company.company_global_info.BankingDetails.builder()
//                                        .iban("UA793073500000037398800001488")
//                                        .remittance_bank("LOL")
//                                        .build())
//                                .build(),
//
//                        EditCompanyGlobalInfoResponse.builder()
//                                .error(new ErrorObject(0))
//                                .build(),
//
//                        Optional.of(Company.builder()
//                                .id(1)
//                                .address("ulica charkivska")
//                                .postal_address("gdeto tam")
//                                .banking_details(null)
//                                .communication(tech.na_app.entity.company.Communication.builder()
//                                        .email("lilchicha@gmail.com")
//                                        .phone_number(List.of("0662744321", "0662744123"))
//                                        .build())
//                                .build())
//                ),
//                //-3-// Company communication is NULL in DB
//                Arguments.of(
//                        EditCompanyGlobalInfoRequest.builder()
//                                .address("ulica lamana")
//                                .postal_address("ylica pupi i lupi")
//                                .communication(Communication.builder()
//                                        .email("pupailupa@gmail.com")
//                                        .phone_number(List.of("0662744321", "0662744123"))
//                                        .build())
//                                .banking_details(tech.na_app.model.company.company_global_info.BankingDetails.builder()
//                                        .iban("UA793073500000037398800001488")
//                                        .remittance_bank("LOL")
//                                        .build())
//                                .build(),
//
//                        EditCompanyGlobalInfoResponse.builder()
//                                .error(new ErrorObject(0))
//                                .build(),
//
//                        Optional.of(Company.builder()
//                                .id(1)
//                                .address("ulica charkivska")
//                                .postal_address("gdeto tam")
//                                .banking_details(BankingDetails.builder()
//                                        .iban("UA793073500000037398800001488")
//                                        .remittance_bank("LOL")
//                                        .build())
//                                .communication(null)
//                                .build())
//                )
//        );
//    }
//
////    @ParameterizedTest
////    @MethodSource("editIdentificationDetails$BadDataSet")
////    void editIdentificationDetails$BadRequest(EditIdentificationDetailsRequest request, EditIdentificationDetailsResponse expectedResponse,
////                                              Optional<Company> mockedCompany
////    ) {
////        User user = User.builder().id(1).companyId(1).build();
////
////        lenient().when(companyRepository.findById(user.getId())).thenReturn(mockedCompany);
////
////        EditIdentificationDetailsResponse response = editIdentificationDetailsServiceImpl.editIdentificationDetails(TestUtils.TEST_REQUEST_ID, user, request);
////
////        assertEquals(expectedResponse, response);
////    }
//
//    private static Stream<Arguments> editIdentificationDetails$BadDataSet() {
//        return Stream.of(
//                //-1-// request is NULL
//                Arguments.of(
//                        null,
//
//                        EditIdentificationDetailsResponse.builder()
//                                .error(new ErrorObject(400, "BAD REQUEST"))
//                                .build(),
//
//                        Optional.of(Company.builder()
//                                .id(1)
//                                .identification_details(tech.na_app.entity.company.IdentificationDetails.builder()
//                                        .edrpou("21312313")
//                                        .registration_certificate("789461346")
//                                        .ipn("11111515111111111")
//                                        .accounting_tax_info("some tax info")
//                                        .tax_form("4")
//                                        .build())
//                                .build())
//
//                ),
//                //-2-// identification_details is NULL
//                Arguments.of(
//                        EditIdentificationDetailsRequest.builder()
//                                .identification_details(null)
//                                .build(),
//
//                        EditIdentificationDetailsResponse.builder()
//                                .error(new ErrorObject(400, "BAD REQUEST"))
//                                .build(),
//
//                        Optional.of(Company.builder()
//                                .id(1)
//                                .identification_details(tech.na_app.entity.company.IdentificationDetails.builder()
//                                        .edrpou("21312313")
//                                        .registration_certificate("789461346")
//                                        .ipn("11111515111111111")
//                                        .accounting_tax_info("some tax info")
//                                        .tax_form("4")
//                                        .build())
//                                .build())
//
//                ),
//                //-3-//  Not found company
//                Arguments.of(
//                        EditIdentificationDetailsRequest.builder()
//                                .identification_details(IdentificationDetails.builder()
//                                        .edrpou("21312313")
//                                        .registration_certificate("789461346")
//                                        .ipn("11111515111111111")
//                                        .accounting_tax_info("some tax info")
//                                        .tax_form("4")
//                                        .build())
//                                .build(),
//
//                        EditIdentificationDetailsResponse.builder()
//                                .error(new ErrorObject(404, "Not Found"))
//                                .build(),
//
//                        Optional.empty()
//
//                )
//        );
//    }
//
////    @ParameterizedTest
////    @MethodSource("editIdentificationDetails$GoodDataSet")
////    void editIdentificationDetails$GoodRequest(EditIdentificationDetailsRequest request, EditIdentificationDetailsResponse expectedResponse,
////                                               Optional<Company> mockedCompany
////    ) {
////        User user = User.builder().id(1).companyId(1).build();
////
////        lenient().when(companyRepository.findById(user.getId())).thenReturn(mockedCompany);
////
////        EditIdentificationDetailsResponse response = editIdentificationDetailsServiceImpl.editIdentificationDetails(TestUtils.TEST_REQUEST_ID, user, request);
////
////        assertEquals(expectedResponse, response);
////    }
//
//    private static Stream<Arguments> editIdentificationDetails$GoodDataSet() {
//        return Stream.of(
//                //-1-//
//                Arguments.of(
//                        EditIdentificationDetailsRequest.builder()
//                                .identification_details(IdentificationDetails.builder()
//                                        .edrpou("21312313")
//                                        .registration_certificate("789461346")
//                                        .ipn("11111515111111111")
//                                        .accounting_tax_info("some tax info")
//                                        .tax_form("4")
//                                        .build())
//                                .build(),
//
//                        EditIdentificationDetailsResponse.builder()
//                                .error(new ErrorObject(0))
//                                .build(),
//
//                        Optional.of(Company.builder()
//                                .id(1)
//                                .identification_details(tech.na_app.entity.company.IdentificationDetails.builder()
//                                        .edrpou("21312313")
//                                        .registration_certificate("789461346")
//                                        .ipn("11111515111111111")
//                                        .accounting_tax_info("some tax info")
//                                        .tax_form("4")
//                                        .build())
//                                .build())
//
//                ),
//                //-2-// Company identification_details is NULL in DB
//                Arguments.of(
//                        EditIdentificationDetailsRequest.builder()
//                                .identification_details(IdentificationDetails.builder()
//                                        .edrpou("21312313")
//                                        .registration_certificate("789461346")
//                                        .ipn("11111515111111111")
//                                        .accounting_tax_info("some tax info")
//                                        .tax_form("4")
//                                        .build())
//                                .build(),
//
//                        EditIdentificationDetailsResponse.builder()
//                                .error(new ErrorObject(0))
//                                .build(),
//
//                        Optional.of(Company.builder()
//                                .id(1)
//                                .identification_details(null)
//                                .build())
//
//                )
//        );
//    }
//
//}