package tech.na_app.services.user_profile;

import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tech.na_app.entity.profile.DrivingLicense;
import tech.na_app.entity.profile.DrivingLicenseSequence;
import tech.na_app.entity.user.User;
import tech.na_app.model.ErrorObject;
import tech.na_app.model.enums.DriverLicenceCategory;
import tech.na_app.model.profile.driving_license.EditInfoDrivingLicenseRequest;
import tech.na_app.model.profile.driving_license.EditInfoDrivingLicenseResponse;
import tech.na_app.model.profile.driving_license.SaveInfoDrivingLicenseRequest;
import tech.na_app.model.profile.driving_license.SaveInfoDrivingLicenseResponse;
import tech.na_app.repository.*;
import tech.na_app.services.user.UserHelperComponent;
import tech.na_app.utils.SequenceGeneratorService;
import tech.na_app.utils.TestUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
    @Mock
    private AvailableDocumentsRepository availableDocumentsRepository;

    private UserProfileService userProfileService;


    @BeforeEach
    public void setUp() {
        userProfileService = new UserProfileService(educationRepository, sequenceGeneratorService, internshipAndInstructionRepository,
                userRepository, getUserProfileHelperComponent, userHelperComponent, drivingLicenseRepository, availableDocumentsRepository);
    }

    @ParameterizedTest
    @MethodSource("saveInfoDrivingLicense$GoodDataSet")
    public void saveInfoDrivingLicense$GoodRequest(SaveInfoDrivingLicenseRequest request, SaveInfoDrivingLicenseResponse expectedResponse,
                                                   Integer mockedUserId, Optional<DrivingLicense> mockedDrivingLicense
    ) {
        //Given
        lenient().when(drivingLicenseRepository.findByUserId(mockedUserId)).thenReturn(mockedDrivingLicense);
        //When
        SaveInfoDrivingLicenseResponse response = userProfileService.saveInfoDrivingLicense(TestUtils.TEST_REQUEST_ID, request);
        //Then
        assertEquals(expectedResponse, response);
    }

    // todo more positive case
    @SneakyThrows
    private static Stream<Arguments> saveInfoDrivingLicense$GoodDataSet() {
        DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        return Stream.of(
                Arguments.of(
                        SaveInfoDrivingLicenseRequest.builder()
                                .categories(Set.of(DriverLicenceCategory.C, DriverLicenceCategory.B))
                                .date_issue(format.parse("15.10.2022"))
                                .date_end(format.parse("15.10.2060"))
                                .userId(1)
                                .build(),

                        SaveInfoDrivingLicenseResponse.builder()
                                .error(new ErrorObject(0))
                                .build(),
                        1,
                        Optional.empty()
                )
        );
    }

    @ParameterizedTest
    @MethodSource("saveInfoDrivingLicense$BadDataSet")
    public void saveInfoDrivingLicense$BadRequest(SaveInfoDrivingLicenseRequest request, SaveInfoDrivingLicenseResponse expectedResponse,
                                                  Integer mockedUserId, Optional<DrivingLicense> mockedDrivingLicense
    ) {
        //Given
        lenient().when(drivingLicenseRepository.findByUserId(mockedUserId)).thenReturn(mockedDrivingLicense);
        //When
        SaveInfoDrivingLicenseResponse response = userProfileService.saveInfoDrivingLicense(TestUtils.TEST_REQUEST_ID, request);
        //Then
        assertEquals(expectedResponse, response);
    }


    @SneakyThrows
    private static Stream<Arguments> saveInfoDrivingLicense$BadDataSet() {
        DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        return Stream.of(
                //categories is NULL
                Arguments.of(
                        SaveInfoDrivingLicenseRequest.builder()
                                .categories(null)
                                .date_issue(format.parse("15.10.2022"))
                                .date_end(format.parse("15.10.2060"))
                                .userId(1)
                                .build(),

                        SaveInfoDrivingLicenseResponse.builder()
                                .error(new ErrorObject(400, "BAD REQUEST"))
                                .build(),
                        1,
                        Optional.empty()
                ),
                //categories are EMPTY
                Arguments.of(
                        SaveInfoDrivingLicenseRequest.builder()
                                .categories(Collections.emptySet())
                                .date_issue(format.parse("15.10.2022"))
                                .date_end(format.parse("15.10.2060"))
                                .userId(1)
                                .build(),

                        SaveInfoDrivingLicenseResponse.builder()
                                .error(new ErrorObject(400, "BAD REQUEST"))
                                .build(),
                        1,
                        Optional.empty()
                ),
                //date_issue is NULL
                Arguments.of(
                        SaveInfoDrivingLicenseRequest.builder()
                                .categories(Set.of(DriverLicenceCategory.C, DriverLicenceCategory.B))
                                .date_issue(null)
                                .date_end(format.parse("15.10.2060"))
                                .userId(1)
                                .build(),

                        SaveInfoDrivingLicenseResponse.builder()
                                .error(new ErrorObject(400, "BAD REQUEST"))
                                .build(),
                        1,
                        Optional.empty()
                ),
                //date_end is NULL
                Arguments.of(
                        SaveInfoDrivingLicenseRequest.builder()
                                .categories(Set.of(DriverLicenceCategory.C, DriverLicenceCategory.B))
                                .date_issue(format.parse("15.10.2022"))
                                .date_end(null)
                                .userId(1)
                                .build(),

                        SaveInfoDrivingLicenseResponse.builder()
                                .error(new ErrorObject(400, "BAD REQUEST"))
                                .build(),
                        1,
                        Optional.empty()
                ),
                //userId is NULL
                Arguments.of(
                        SaveInfoDrivingLicenseRequest.builder()
                                .categories(Set.of(DriverLicenceCategory.C, DriverLicenceCategory.B))
                                .date_issue(format.parse("15.10.2022"))
                                .date_end(format.parse("15.10.2060"))
                                .userId(null)
                                .build(),

                        SaveInfoDrivingLicenseResponse.builder()
                                .error(new ErrorObject(400, "BAD REQUEST"))
                                .build(),
                        1,
                        Optional.empty()
                ),
                //User already has driving license
                Arguments.of(
                        SaveInfoDrivingLicenseRequest.builder()
                                .categories(Set.of(DriverLicenceCategory.C, DriverLicenceCategory.B))
                                .date_issue(format.parse("15.10.2022"))
                                .date_end(format.parse("15.10.2060"))
                                .userId(1)
                                .build(),

                        SaveInfoDrivingLicenseResponse.builder()
                                .error(new ErrorObject(400, "User already has driving license"))
                                .build(),
                        1,
                        Optional.of(DrivingLicense.builder()
                                .id("1")
                                .userId(1)
                                .date_issue(format.parse("15.10.2022"))
                                .date_end(format.parse("15.10.2060"))
                                .categories(Set.of(DriverLicenceCategory.C, DriverLicenceCategory.B))
                                .build())
                )
        );
    }

    @ParameterizedTest
    @MethodSource("editInfoDrivingLicense$GoodDataSet")
    public void editInfoDrivingLicense$GoodRequest(EditInfoDrivingLicenseRequest request, EditInfoDrivingLicenseResponse expectedResponse,
                                                   Integer mockedUserId, Optional<DrivingLicense> mockedDrivingLicense
    ) {
        //Given
        User user = User.builder().id(mockedUserId).build();

        lenient().when(drivingLicenseRepository.findByUserId(mockedUserId)).thenReturn(mockedDrivingLicense);
        lenient().when(userRepository.findById(mockedUserId)).thenReturn(Optional.of(User.builder().id(1).build()));

        //When
        EditInfoDrivingLicenseResponse response = userProfileService.editInfoDrivingLicense(TestUtils.TEST_REQUEST_ID, user, request);
        //Then
        assertEquals(expectedResponse, response);
    }

    @SneakyThrows
    private static Stream<Arguments> editInfoDrivingLicense$GoodDataSet() {
        DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        return Stream.of(
                Arguments.of(
                        EditInfoDrivingLicenseRequest.builder()
                                .categories(Set.of(DriverLicenceCategory.C, DriverLicenceCategory.B))
                                .date_issue(format.parse("15.10.2022"))
                                .date_end(format.parse("15.10.2060"))
                                .userId(1)
                                .build(),

                        EditInfoDrivingLicenseResponse.builder()
                                .error(new ErrorObject(0))
                                .build(),
                        1,
                        Optional.of(DrivingLicense.builder()
                                .id("1")
                                .userId(1)
                                .date_issue(format.parse("15.10.2022"))
                                .date_end(format.parse("15.10.2060"))
                                .categories(Set.of(DriverLicenceCategory.C, DriverLicenceCategory.B))
                                .build())
                ),

                Arguments.of(
                        EditInfoDrivingLicenseRequest.builder()
                                .categories(Set.of(DriverLicenceCategory.C, DriverLicenceCategory.B))
                                .date_issue(format.parse("15.10.2022"))
                                .date_end(format.parse("15.10.2060"))
                                .userId(null)
                                .build(),

                        EditInfoDrivingLicenseResponse.builder()
                                .error(new ErrorObject(0))
                                .build(),
                        1,
                        Optional.of(DrivingLicense.builder()
                                .id("1")
                                .userId(1)
                                .date_issue(format.parse("15.10.2022"))
                                .date_end(format.parse("15.10.2060"))
                                .categories(Set.of(DriverLicenceCategory.C, DriverLicenceCategory.B))
                                .build())
                )
        );
    }

    @ParameterizedTest
    @MethodSource("editInfoDrivingLicense$BadDataSet")
    public void editInfoDrivingLicense$BadRequest(EditInfoDrivingLicenseRequest request, EditInfoDrivingLicenseResponse expectedResponse,
                                                  Integer mockedUserId, Optional<DrivingLicense> mockedDrivingLicense
    ) {
        //Given
        User user = User.builder().id(mockedUserId).build();

        lenient().when(drivingLicenseRepository.findByUserId(mockedUserId)).thenReturn(mockedDrivingLicense);
        lenient().when(userRepository.findById(mockedUserId)).thenReturn(Optional.of(User.builder().id(1).build()));

        //When
        EditInfoDrivingLicenseResponse response = userProfileService.editInfoDrivingLicense(TestUtils.TEST_REQUEST_ID, user, request);
        //Then
        assertEquals(expectedResponse, response);
    }


    @SneakyThrows
    private static Stream<Arguments> editInfoDrivingLicense$BadDataSet() {
        DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        return Stream.of(
//                categories is NULL
                Arguments.of(
                        EditInfoDrivingLicenseRequest.builder()
                                .categories(null)
                                .date_issue(format.parse("15.10.2022"))
                                .date_end(format.parse("15.10.2060"))
                                .userId(1)
                                .build(),

                        EditInfoDrivingLicenseResponse.builder()
                                .error(new ErrorObject(400, "BAD REQUEST"))
                                .build(),
                        1,
                        Optional.of(DrivingLicense.builder()
                                .id("1")
                                .userId(1)
                                .date_issue(format.parse("15.10.2022"))
                                .date_end(format.parse("15.10.2060"))
                                .categories(Set.of(DriverLicenceCategory.C, DriverLicenceCategory.B))
                                .build())
                ),
                //categories are EMPTY
                Arguments.of(
                        EditInfoDrivingLicenseRequest.builder()
                                .categories(Collections.emptySet())
                                .date_issue(format.parse("15.10.2022"))
                                .date_end(format.parse("15.10.2060"))
                                .userId(1)
                                .build(),

                        EditInfoDrivingLicenseResponse.builder()
                                .error(new ErrorObject(400, "BAD REQUEST"))
                                .build(),
                        1,
                        Optional.of(DrivingLicense.builder()
                                .id("1")
                                .userId(1)
                                .date_issue(format.parse("15.10.2022"))
                                .date_end(format.parse("15.10.2060"))
                                .categories(Set.of(DriverLicenceCategory.C, DriverLicenceCategory.B))
                                .build())
                ),
                //date_issue is NULL
                Arguments.of(
                        EditInfoDrivingLicenseRequest.builder()
                                .categories(Set.of(DriverLicenceCategory.C, DriverLicenceCategory.B))
                                .date_issue(null)
                                .date_end(format.parse("15.10.2060"))
                                .userId(1)
                                .build(),

                        EditInfoDrivingLicenseResponse.builder()
                                .error(new ErrorObject(400, "BAD REQUEST"))
                                .build(),
                        1,
                        Optional.of(DrivingLicense.builder()
                                .id("1")
                                .userId(1)
                                .date_issue(format.parse("15.10.2022"))
                                .date_end(format.parse("15.10.2060"))
                                .categories(Set.of(DriverLicenceCategory.C, DriverLicenceCategory.B))
                                .build())
                ),
                //date_end is NULL
                Arguments.of(
                        EditInfoDrivingLicenseRequest.builder()
                                .categories(Set.of(DriverLicenceCategory.C, DriverLicenceCategory.B))
                                .date_issue(format.parse("15.10.2022"))
                                .date_end(null)
                                .userId(1)
                                .build(),

                        EditInfoDrivingLicenseResponse.builder()
                                .error(new ErrorObject(400, "BAD REQUEST"))
                                .build(),
                        1,
                        Optional.of(DrivingLicense.builder()
                                .id("1")
                                .userId(1)
                                .date_issue(format.parse("15.10.2022"))
                                .date_end(format.parse("15.10.2060"))
                                .categories(Set.of(DriverLicenceCategory.C, DriverLicenceCategory.B))
                                .build())
                ),
                // No license
                Arguments.of(
                        EditInfoDrivingLicenseRequest.builder()
                                .categories(Set.of(DriverLicenceCategory.C, DriverLicenceCategory.B))
                                .date_issue(format.parse("15.10.2022"))
                                .date_end(format.parse("15.10.2060"))
                                .userId(1)
                                .build(),

                        EditInfoDrivingLicenseResponse.builder()
                                .error(new ErrorObject(400, "BAD REQUEST"))
                                .build(),
                        1,
                        Optional.empty()
                )
        );
    }
}