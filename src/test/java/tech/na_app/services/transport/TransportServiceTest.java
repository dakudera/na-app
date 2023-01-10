package tech.na_app.services.transport;

import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tech.na_app.converter.TransportConverter;
import tech.na_app.entity.transport.Transport;
import tech.na_app.model.ErrorObject;
import tech.na_app.model.transport.nomenclature_name.EditNomenclatureNameRequest;
import tech.na_app.model.transport.nomenclature_name.EditNomenclatureNameResponse;
import tech.na_app.model.transport.technical_certificate.EditTechnicalCertificateRequest;
import tech.na_app.model.transport.technical_certificate.EditTechnicalCertificateResponse;
import tech.na_app.repository.TransportRepository;
import tech.na_app.utils.SequenceGeneratorService;
import tech.na_app.utils.TestUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.lenient;


@ExtendWith({MockitoExtension.class})
class TransportServiceTest {

    @Mock
    private SequenceGeneratorService sequenceGeneratorService;

    @Mock
    private TransportRepository transportRepository;

    @Mock
    private TransportConverter transportConverter;

    private TransportService transportService;

    @BeforeEach
    public void setUp() {
        transportService = new TransportService(sequenceGeneratorService, transportRepository, transportConverter);
    }

    @ParameterizedTest
    @MethodSource("editTechnicalCertificate$BadDataSet")
    void editTechnicalCertificate$BadRequest(EditTechnicalCertificateRequest request, EditTechnicalCertificateResponse expectedResponse,
                                             Optional<Transport> mockedTransport
    ) {
        //Given
        lenient().when(transportRepository.findById(1)).thenReturn(mockedTransport);
        //When
        EditTechnicalCertificateResponse response = transportService.editTechnicalCertificate(TestUtils.TEST_REQUEST_ID, request);
        //Then
        assertEquals(expectedResponse, response);
    }

    @SneakyThrows
    private static Stream<Arguments> editTechnicalCertificate$BadDataSet() {
        DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        return Stream.of(

                //-1-// request is NULL
                Arguments.of(
                        null,

                        EditTechnicalCertificateResponse.builder()
                                .error(new ErrorObject(400, "BAD REQUEST"))
                                .build(),

                        Optional.of(Transport.builder()
                                .id(1)
                                .build())
                ),

                //-2-// id is NULL
                Arguments.of(
                        EditTechnicalCertificateRequest.builder()
                                .id(null)
                                .technical_certificate(EditTechnicalCertificateRequest.TechnicalCertificate.builder()
                                        .num_and_series("123qwe")
                                        .issued_by("113325")
                                        .date_issue(format.parse("15.10.2022"))
                                        .date_end(format.parse("15.10.2060"))
                                        .build())
                                .build(),

                        EditTechnicalCertificateResponse.builder()
                                .error(new ErrorObject(400, "BAD REQUEST"))
                                .build(),

                        Optional.of(Transport.builder()
                                .id(1)
                                .build())
                ),

                //-3-// technical_certificate is NULL
                Arguments.of(
                        EditTechnicalCertificateRequest.builder()
                                .id(1)
                                .technical_certificate(null)
                                .build(),

                        EditTechnicalCertificateResponse.builder()
                                .error(new ErrorObject(400, "BAD REQUEST"))
                                .build(),

                        Optional.of(Transport.builder()
                                .id(1)
                                .build())
                ),

                //-4-// not fond transport in DB
                Arguments.of(
                        EditTechnicalCertificateRequest.builder()
                                .id(1)
                                .technical_certificate(EditTechnicalCertificateRequest.TechnicalCertificate.builder()
                                        .num_and_series("123qwe")
                                        .issued_by("113325")
                                        .date_issue(format.parse("15.10.2022"))
                                        .date_end(format.parse("15.10.2060"))
                                        .build())
                                .build(),

                        EditTechnicalCertificateResponse.builder()
                                .error(new ErrorObject(404, "Not Found"))
                                .build(),

                        Optional.empty()
                )
        );
    }

    @ParameterizedTest
    @MethodSource("editTechnicalCertificate$GoodDataSet")
    void editTechnicalCertificate$GoodRequest(EditTechnicalCertificateRequest request, EditTechnicalCertificateResponse expectedResponse,
                                              Optional<Transport> mockedTransport
    ) {
        //Given
        lenient().when(transportRepository.findById(1)).thenReturn(mockedTransport);
        //When
        EditTechnicalCertificateResponse response = transportService.editTechnicalCertificate(TestUtils.TEST_REQUEST_ID, request);
        //Then
        assertEquals(expectedResponse, response);
    }

    @SneakyThrows
    private static Stream<Arguments> editTechnicalCertificate$GoodDataSet() {
        DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        return Stream.of(

                //-1-//
                Arguments.of(
                        EditTechnicalCertificateRequest.builder()
                                .id(1)
                                .technical_certificate(EditTechnicalCertificateRequest.TechnicalCertificate.builder()
                                        .num_and_series("123qwe")
                                        .issued_by("113325")
                                        .date_issue(format.parse("15.10.2022"))
                                        .date_end(format.parse("15.10.2060"))
                                        .build())
                                .build(),

                        EditTechnicalCertificateResponse.builder()
                                .error(new ErrorObject(0))
                                .build(),

                        Optional.of(Transport.builder()
                                .id(1)
                                .build())
                )
        );
    }

    @ParameterizedTest
    @MethodSource("editNomenclatureName$BadDataSet")
    void editNomenclatureName$BadRequest(EditNomenclatureNameRequest request, EditNomenclatureNameResponse expectedResponse,
                                         Optional<Transport> mockedTransport
    ) {
        //Given
        lenient().when(transportRepository.findById(1)).thenReturn(mockedTransport);
        //When
        EditNomenclatureNameResponse response = transportService.editNomenclatureName(TestUtils.TEST_REQUEST_ID, request);
        //Then
        assertEquals(expectedResponse, response);
    }

    private static Stream<Arguments> editNomenclatureName$BadDataSet() {
        return Stream.of(

                //-1-// request is NULL
                Arguments.of(
                        null,

                        EditNomenclatureNameResponse.builder()
                                .error(new ErrorObject(400, "BAD REQUEST"))
                                .build(),

                        Optional.of(Transport.builder()
                                .id(1)
                                .build())
                ),

                //-2-// nomenclature_name is NULL
                Arguments.of(
                        EditNomenclatureNameRequest.builder()
                                .id(1)
                                .nomenclature_name(null)
                                .build(),

                        EditNomenclatureNameResponse.builder()
                                .error(new ErrorObject(400, "BAD REQUEST"))
                                .build(),

                        Optional.of(Transport.builder()
                                .id(1)
                                .build())
                ),

                //-3-// not fond transport in DB
                Arguments.of(
                        EditNomenclatureNameRequest.builder()
                                .id(1)
                                .nomenclature_name("TokiyskiyJiGul")
                                .build(),

                        EditNomenclatureNameResponse.builder()
                                .error(new ErrorObject(404, "Not Found"))
                                .build(),

                        Optional.empty()
                ),

                //-4-// id is NULL
                Arguments.of(
                        EditNomenclatureNameRequest.builder()
                                .id(null)
                                .nomenclature_name("TokiyskiyJiGul")
                                .build(),

                        EditNomenclatureNameResponse.builder()
                                .error(new ErrorObject(400, "BAD REQUEST"))
                                .build(),

                        Optional.of(Transport.builder()
                                .id(1)
                                .build())
                )
        );
    }

    @ParameterizedTest
    @MethodSource("editNomenclatureName$GoodDataSet")
    void editNomenclatureName$GoodRequest(EditNomenclatureNameRequest request, EditNomenclatureNameResponse expectedResponse,
                                          Optional<Transport> mockedTransport
    ) {
        //Given
        lenient().when(transportRepository.findById(1)).thenReturn(mockedTransport);
        //When
        EditNomenclatureNameResponse response = transportService.editNomenclatureName(TestUtils.TEST_REQUEST_ID, request);
        //Then
        assertEquals(expectedResponse, response);
    }

    private static Stream<Arguments> editNomenclatureName$GoodDataSet() {
        return Stream.of(

                //-1-//
                Arguments.of(
                        EditNomenclatureNameRequest.builder()
                                .id(1)
                                .nomenclature_name("TokiyskiyJiGul")
                                .build(),

                        EditNomenclatureNameResponse.builder()
                                .error(new ErrorObject(0))
                                .build(),

                        Optional.of(Transport.builder()
                                .id(1)
                                .build()
                        )
                )
        );
    }
}