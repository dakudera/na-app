package tech.na_app.services.user_profile;

import lombok.SneakyThrows;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tech.na_app.entity.profile.DrivingLicense;
import tech.na_app.entity.profile.DrivingLicenseSequence;
import tech.na_app.model.ErrorObject;
import tech.na_app.model.enums.DriverLicenceCategory;
import tech.na_app.model.profile.driving_license.SaveInfoDrivingLicenseRequest;
import tech.na_app.model.profile.driving_license.SaveInfoDrivingLicenseResponse;
import tech.na_app.repository.DrivingLicenseRepository;
import tech.na_app.repository.EducationRepository;
import tech.na_app.repository.InternshipAndInstructionRepository;
import tech.na_app.repository.UserRepository;
import tech.na_app.services.user.UserHelperComponent;
import tech.na_app.utils.SequenceGeneratorService;
import tech.na_app.utils.TestUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import static org.mockito.Mockito.lenient;

@ExtendWith({MockitoExtension.class})
public class UserProfileServiceTest {

    @Mock
    private EducationRepository educationRepository;

    @Mock
    private SequenceGeneratorService sequenceGeneratorService;

    @Mock
    private InternshipAndInstructionRepository internshipAndInstructionRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private GetUserProfileHelperComponent getUserProfileHelperComponent;

    @Mock
    private UserHelperComponent userHelperComponent;

    @Mock
    private DrivingLicenseRepository drivingLicenseRepository;

    private UserProfileService userProfileService;


    @BeforeEach
    public void setUp() {
        userProfileService = new UserProfileService(educationRepository, sequenceGeneratorService, internshipAndInstructionRepository,
                userRepository, getUserProfileHelperComponent, userHelperComponent, drivingLicenseRepository);
    }


    @ParameterizedTest
    @MethodSource("saveInfoDrivingLicense$BadDataSet")
    public void saveInfoDrivingLicense$BadRequest(SaveInfoDrivingLicenseRequest request, SaveInfoDrivingLicenseResponse expectedResponse) {
        //Given
        lenient().when(drivingLicenseRepository.findByUserId(request.getUserId())).thenReturn(Optional.empty());
        lenient().when((DrivingLicenseSequence) sequenceGeneratorService.getSequenceNumber(DrivingLicense.SEQUENCE_NAME, DrivingLicenseSequence.class))
                .thenReturn(DrivingLicenseSequence.builder()
                        .id("test")
                        .seq(1)
                        .build());
        //When
        SaveInfoDrivingLicenseResponse response = userProfileService.saveInfoDrivingLicense(TestUtils.TEST_REQUEST_ID, request);
        //Then
        Assertions.assertEquals(expectedResponse, response);

    }


    @SneakyThrows
    private static Stream<Arguments> saveInfoDrivingLicense$BadDataSet() {
        DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        return Stream.of(
                Arguments.of(
                        SaveInfoDrivingLicenseRequest.builder()
                                .categories(null)
                                .date_issue(format.parse("15.10.2022"))
                                .date_end(format.parse("15.10.2060"))
                                .userId(25)
                                .build(),

                        SaveInfoDrivingLicenseResponse.builder()
                                .error(new ErrorObject(400, "BAD REQUEST"))
                                .build()
                ),
                Arguments.of(
                        SaveInfoDrivingLicenseRequest.builder()
                                .categories(Set.of(DriverLicenceCategory.C, DriverLicenceCategory.B))
                                .date_issue(null)
                                .date_end(format.parse("15.10.2060"))
                                .userId(25)
                                .build(),

                        SaveInfoDrivingLicenseResponse.builder()
                                .error(new ErrorObject(400, "BAD REQUEST"))
                                .build()
                ),
                Arguments.of(
                        SaveInfoDrivingLicenseRequest.builder()
                                .categories(Set.of(DriverLicenceCategory.C, DriverLicenceCategory.B))
                                .date_issue(format.parse("15.10.2022"))
                                .date_end(null)
                                .userId(25)
                                .build(),

                        SaveInfoDrivingLicenseResponse.builder()
                                .error(new ErrorObject(400, "BAD REQUEST"))
                                .build()
                ),
                Arguments.of(
                        SaveInfoDrivingLicenseRequest.builder()
                                .categories(Set.of(DriverLicenceCategory.C, DriverLicenceCategory.B))
                                .date_issue(format.parse("15.10.2022"))
                                .date_end(format.parse("15.10.2060"))
                                .userId(null)
                                .build(),

                        SaveInfoDrivingLicenseResponse.builder()
                                .error(new ErrorObject(400, "BAD REQUEST"))
                                .build()
                )
        );
    }

    @Test
    void editInfoDrivingLicense() {
    }
}