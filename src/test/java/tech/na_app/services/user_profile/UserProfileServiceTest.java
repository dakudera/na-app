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
import tech.na_app.model.enums.UserRoleType;
import tech.na_app.model.profile.EditUserProfileRequest;
import tech.na_app.model.profile.EditUserProfileResponse;
import tech.na_app.model.profile.driving_license.EditInfoDrivingLicenseRequest;
import tech.na_app.model.profile.driving_license.EditInfoDrivingLicenseResponse;
import tech.na_app.model.profile.driving_license.SaveInfoDrivingLicenseRequest;
import tech.na_app.model.profile.driving_license.SaveInfoDrivingLicenseResponse;
import tech.na_app.repository.*;
import tech.na_app.services.user.UserHelperComponent;
import tech.na_app.utils.SequenceGeneratorService;
import tech.na_app.utils.TestUtils;

import java.math.BigDecimal;
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
        lenient().when((DrivingLicenseSequence) sequenceGeneratorService.getSequenceNumber(DrivingLicense.SEQUENCE_NAME, DrivingLicenseSequence.class))
                .thenReturn(DrivingLicenseSequence.builder()
                        .id("test")
                        .seq(1)
                        .build());
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
                //-1-//
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
        lenient().when((DrivingLicenseSequence) sequenceGeneratorService.getSequenceNumber(DrivingLicense.SEQUENCE_NAME, DrivingLicenseSequence.class))
                .thenReturn(DrivingLicenseSequence.builder()
                        .id("test")
                        .seq(1)
                        .build());
        //When
        SaveInfoDrivingLicenseResponse response = userProfileService.saveInfoDrivingLicense(TestUtils.TEST_REQUEST_ID, request);
        //Then
        assertEquals(expectedResponse, response);
    }


    @SneakyThrows
    private static Stream<Arguments> saveInfoDrivingLicense$BadDataSet() {
        DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        return Stream.of(
                //-1-// categories is NULL
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
                //-2-// categories are EMPTY
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
                //-3-// date_issue is NULL
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
                //-4-// date_end is NULL
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
                //-5-// userId is NULL
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
                //-6-// User already has driving license
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
                                .id(1)
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
                //-1-// edit other user
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
                                .id(1)
                                .userId(1)
                                .date_issue(format.parse("15.10.2022"))
                                .date_end(format.parse("15.10.2060"))
                                .categories(Set.of(DriverLicenceCategory.C, DriverLicenceCategory.B))
                                .build())
                ),

                //-2-// edit self user
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
                                .id(1)
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
                //-1-// categories is NULL
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
                                .id(1)
                                .userId(1)
                                .date_issue(format.parse("15.10.2022"))
                                .date_end(format.parse("15.10.2060"))
                                .categories(Set.of(DriverLicenceCategory.C, DriverLicenceCategory.B))
                                .build())
                ),
                //-2-// categories are EMPTY
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
                                .id(1)
                                .userId(1)
                                .date_issue(format.parse("15.10.2022"))
                                .date_end(format.parse("15.10.2060"))
                                .categories(Set.of(DriverLicenceCategory.C, DriverLicenceCategory.B))
                                .build())
                ),
                //-3-// date_issue is NULL
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
                                .id(1)
                                .userId(1)
                                .date_issue(format.parse("15.10.2022"))
                                .date_end(format.parse("15.10.2060"))
                                .categories(Set.of(DriverLicenceCategory.C, DriverLicenceCategory.B))
                                .build())
                ),
                //-4-// date_end is NULL
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
                                .id(1)
                                .userId(1)
                                .date_issue(format.parse("15.10.2022"))
                                .date_end(format.parse("15.10.2060"))
                                .categories(Set.of(DriverLicenceCategory.C, DriverLicenceCategory.B))
                                .build())
                ),
                //-5-// No license
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

    @ParameterizedTest
    @MethodSource("editUserProfile$GoodDataSet")
    void editUserProfile$GoodRequest(EditUserProfileRequest request, EditUserProfileResponse expectedResponse,
                                     User mockedUser, Optional<User> mockedAnotherUser
    ) {
        //Given
        lenient().when(userRepository.findById(1)).thenReturn(mockedAnotherUser);
        //When
        EditUserProfileResponse response = userProfileService.editUserProfile(TestUtils.TEST_REQUEST_ID, mockedUser, request);
        //Then
        assertEquals(expectedResponse, response);
    }

    @SneakyThrows
    private static Stream<Arguments> editUserProfile$GoodDataSet() {
        DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        return Stream.of(
                //-1-// CHIEF_ACCOUNTANT try to change self user from CHIEF_ACCOUNTANT to DRIVER
                Arguments.of(
                        EditUserProfileRequest.builder()
                                .role(UserRoleType.DRIVER)
                                .email("absdba@gmail.com")
                                .phone("+380662744888")
                                .fio("ВАСИЛЬ")
                                .acc_order_number("123456")
                                .acc_order_date(format.parse("15.10.2022"))
                                .salary(BigDecimal.valueOf(12563))
                                .birthday(format.parse("13.11.1983"))
                                .previous_work_exp("10")
                                .previous_info_work_mp("12")
                                .sufficient_experience_mp("12")
                                .registration_address("Dnipro city, Faina street")
                                .actual_address("Dnipro city, Faina street")
                                .build(),

                        EditUserProfileResponse.builder()
                                .error(new ErrorObject(0))
                                .build(),

                        //User that make request
                        User.builder()
                                .id(777)
                                .role(UserRoleType.CHIEF_ACCOUNTANT)
                                .build(),

                        //Another user
                        Optional.of(User.builder()
                                .id(1)
                                .role(UserRoleType.CHIEF_ACCOUNTANT)
                                .build())
                ),
                //-2-// SUPER_ADMIN try to change other user from DIRECTOR to EXECUTIVE_DIRECTOR
                Arguments.of(
                        EditUserProfileRequest.builder()
                                .id(1)
                                .role(UserRoleType.EXECUTIVE_DIRECTOR)
                                .email("absdba@gmail.com")
                                .phone("+380662744888")
                                .fio("ВАСИЛЬ")
                                .acc_order_number("123456")
                                .acc_order_date(format.parse("15.10.2022"))
                                .salary(BigDecimal.valueOf(12563))
                                .birthday(format.parse("13.11.1983"))
                                .previous_work_exp("10")
                                .previous_info_work_mp("12")
                                .sufficient_experience_mp("12")
                                .registration_address("Dnipro city, Faina street")
                                .actual_address("Dnipro city, Faina street")
                                .build(),

                        EditUserProfileResponse.builder()
                                .error(new ErrorObject(0))
                                .build(),

                        //User that make request
                        User.builder()
                                .id(777)
                                .role(UserRoleType.SUPER_ADMIN)
                                .build(),

                        //Another user
                        Optional.of(User.builder()
                                .id(1)
                                .role(UserRoleType.DIRECTOR)
                                .build())

                ),
                //-3-// SUPER_ADMIN try to change other user from EXECUTIVE_DIRECTOR to DIRECTOR
                Arguments.of(
                        EditUserProfileRequest.builder()
                                .id(1)
                                .role(UserRoleType.DIRECTOR)
                                .email("absdba@gmail.com")
                                .phone("+380662744888")
                                .fio("ВАСИЛЬ")
                                .acc_order_number("123456")
                                .acc_order_date(format.parse("15.10.2022"))
                                .salary(BigDecimal.valueOf(12563))
                                .birthday(format.parse("13.11.1983"))
                                .previous_work_exp("10")
                                .previous_info_work_mp("12")
                                .sufficient_experience_mp("12")
                                .registration_address("Dnipro city, Faina street")
                                .actual_address("Dnipro city, Faina street")
                                .build(),

                        EditUserProfileResponse.builder()
                                .error(new ErrorObject(0))
                                .build(),

                        //User that make request
                        User.builder()
                                .id(777)
                                .role(UserRoleType.SUPER_ADMIN)
                                .build(),

                        //Another user
                        Optional.of(User.builder()
                                .id(1)
                                .role(UserRoleType.EXECUTIVE_DIRECTOR)
                                .build())

                )
        );
    }

    @ParameterizedTest
    @MethodSource("editUserProfile$BadDataSet")
    void editUserProfile$BadRequest(EditUserProfileRequest request, EditUserProfileResponse expectedResponse,
                                    User mockedUser, Optional<User> mockedAnotherUser
    ) {
        //Given
        lenient().when(userRepository.findById(1)).thenReturn(mockedAnotherUser);
        //When
        EditUserProfileResponse response = userProfileService.editUserProfile(TestUtils.TEST_REQUEST_ID, mockedUser, request);
        //Then
        assertEquals(expectedResponse, response);
    }

    @SneakyThrows
    private static Stream<Arguments> editUserProfile$BadDataSet() {
        DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        return Stream.of(
                //-1-// CHIEF_ACCOUNTANT try to change other user from CHIEF_ACCOUNTANT to DRIVER
                Arguments.of(
                        EditUserProfileRequest.builder()
                                .id(1)
                                .role(UserRoleType.DRIVER)
                                .email("absdba@gmail.com")
                                .phone("+380662744888")
                                .fio("ВАСИЛЬ")
                                .acc_order_number("123456")
                                .acc_order_date(format.parse("15.10.2022"))
                                .salary(BigDecimal.valueOf(12563))
                                .birthday(format.parse("13.11.1983"))
                                .previous_work_exp("10")
                                .previous_info_work_mp("12")
                                .sufficient_experience_mp("12")
                                .registration_address("Dnipro city, Faina street")
                                .actual_address("Dnipro city, Faina street")
                                .build(),

                        EditUserProfileResponse.builder()
                                .error(new ErrorObject(500, "denied"))
                                .build(),

                        //User that make request
                        User.builder()
                                .id(777)
                                .role(UserRoleType.CHIEF_ACCOUNTANT)
                                .build(),

                        //Another user
                        Optional.of(User.builder()
                                .id(1)
                                .role(UserRoleType.CHIEF_ACCOUNTANT)
                                .build())
                ),
                //-2-// CHIEF_ACCOUNTANT try to change self user from CHIEF_ACCOUNTANT to DIRECTOR
                Arguments.of(
                        EditUserProfileRequest.builder()
                                .role(UserRoleType.DIRECTOR)
                                .email("absdba@gmail.com")
                                .phone("+380662744888")
                                .fio("ВАСИЛЬ")
                                .acc_order_number("123456")
                                .acc_order_date(format.parse("15.10.2022"))
                                .salary(BigDecimal.valueOf(12563))
                                .birthday(format.parse("13.11.1983"))
                                .previous_work_exp("10")
                                .previous_info_work_mp("12")
                                .sufficient_experience_mp("12")
                                .registration_address("Dnipro city, Faina street")
                                .actual_address("Dnipro city, Faina street")
                                .build(),

                        EditUserProfileResponse.builder()
                                .error(new ErrorObject(500, "denied"))
                                .build(),

                        //User that make request
                        User.builder()
                                .id(777)
                                .role(UserRoleType.CHIEF_ACCOUNTANT)
                                .build(),

                        //Another user
                        Optional.of(User.builder()
                                .id(1)
                                .role(UserRoleType.CHIEF_ACCOUNTANT)
                                .build())
                ),
                //-3-// CHIEF_ACCOUNTANT try to change another user from DIRECTOR to CHIEF_ACCOUNTANT
                Arguments.of(
                        EditUserProfileRequest.builder()
                                .id(1)
                                .role(UserRoleType.CHIEF_ACCOUNTANT)
                                .email("absdba@gmail.com")
                                .phone("+380662744888")
                                .fio("ВАСИЛЬ")
                                .acc_order_number("123456")
                                .acc_order_date(format.parse("15.10.2022"))
                                .salary(BigDecimal.valueOf(12563))
                                .birthday(format.parse("13.11.1983"))
                                .previous_work_exp("10")
                                .previous_info_work_mp("12")
                                .sufficient_experience_mp("12")
                                .registration_address("Dnipro city, Faina street")
                                .actual_address("Dnipro city, Faina street")
                                .build(),

                        EditUserProfileResponse.builder()
                                .error(new ErrorObject(500, "denied"))
                                .build(),

                        //User that make request
                        User.builder()
                                .id(777)
                                .role(UserRoleType.CHIEF_ACCOUNTANT)
                                .build(),

                        //Another user
                        Optional.of(User.builder()
                                .id(1)
                                .role(UserRoleType.DIRECTOR)
                                .build())
                ),
                //-4-// not valid email
                Arguments.of(
                        EditUserProfileRequest.builder()
                                .id(1)
                                .role(UserRoleType.EXECUTIVE_DIRECTOR)
                                .email("dssak34ojqw")
                                .phone("+380662744888")
                                .fio("ВАСИЛЬ")
                                .acc_order_number("123456")
                                .acc_order_date(format.parse("15.10.2022"))
                                .salary(BigDecimal.valueOf(12563))
                                .birthday(format.parse("13.11.1983"))
                                .previous_work_exp("10")
                                .previous_info_work_mp("12")
                                .sufficient_experience_mp("12")
                                .registration_address("Dnipro city, Faina street")
                                .actual_address("Dnipro city, Faina street")
                                .build(),

                        EditUserProfileResponse.builder()
                                .error(new ErrorObject(500, "Email is invalid"))
                                .build(),

                        //User that make request
                        User.builder()
                                .id(777)
                                .role(UserRoleType.SUPER_ADMIN)
                                .build(),

                        //Another user
                        Optional.of(User.builder()
                                .id(1)
                                .role(UserRoleType.DIRECTOR)
                                .build())
                ),
                //-5-// role is NULL
                Arguments.of(
                        EditUserProfileRequest.builder()
                                .id(1)
                                .role(null)
                                .email("absdba@gmail.com")
                                .phone("+380662744888")
                                .fio("ВАСИЛЬ")
                                .acc_order_number("123456")
                                .acc_order_date(format.parse("15.10.2022"))
                                .salary(BigDecimal.valueOf(12563))
                                .birthday(format.parse("13.11.1983"))
                                .previous_work_exp("10")
                                .previous_info_work_mp("12")
                                .sufficient_experience_mp("12")
                                .registration_address("Dnipro city, Faina street")
                                .actual_address("Dnipro city, Faina street")
                                .build(),

                        EditUserProfileResponse.builder()
                                .error(new ErrorObject(400, "BAD REQUEST"))
                                .build(),

                        //User that make request
                        User.builder()
                                .id(777)
                                .role(UserRoleType.SUPER_ADMIN)
                                .build(),

                        //Another user
                        Optional.of(User.builder()
                                .id(1)
                                .role(UserRoleType.DIRECTOR)
                                .build())
                ),
                //-6-// request is NULL
                Arguments.of(
                        null,

                        EditUserProfileResponse.builder()
                                .error(new ErrorObject(400, "BAD REQUEST"))
                                .build(),

                        //User that make request
                        User.builder()
                                .id(777)
                                .role(UserRoleType.SUPER_ADMIN)
                                .build(),

                        //Another user
                        Optional.of(User.builder()
                                .id(1)
                                .role(UserRoleType.DIRECTOR)
                                .build())
                ),
                //-7-// Another user is NULL or EMPTY
                Arguments.of(
                        EditUserProfileRequest.builder()
                                .id(1)
                                .role(UserRoleType.EXECUTIVE_DIRECTOR)
                                .email("absdba@gmail.com")
                                .phone("+380662744888")
                                .fio("ВАСИЛЬ")
                                .acc_order_number("123456")
                                .acc_order_date(format.parse("15.10.2022"))
                                .salary(BigDecimal.valueOf(12563))
                                .birthday(format.parse("13.11.1983"))
                                .previous_work_exp("10")
                                .previous_info_work_mp("12")
                                .sufficient_experience_mp("12")
                                .registration_address("Dnipro city, Faina street")
                                .actual_address("Dnipro city, Faina street")
                                .build(),

                        EditUserProfileResponse.builder()
                                .error(new ErrorObject(400, "User was not found"))
                                .build(),

                        //User that make request
                        User.builder()
                                .id(777)
                                .role(UserRoleType.SUPER_ADMIN)
                                .build(),

                        //Another user
                        Optional.empty()
                )
        );
    }
}