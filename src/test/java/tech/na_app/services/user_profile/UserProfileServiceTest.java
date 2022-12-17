package tech.na_app.services.user_profile;

import org.junit.Before;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.Assert.*;


@SpringBootTest
@RunWith(PowerMockRunner.class)
@PowerMockIgnore("javax.management.*")
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

    @Autowired
    private UserProfileService userProfileService;

    @Before
    public void setUp() {
        userProfileService = new UserProfileService(educationRepository, sequenceGeneratorService, internshipAndInstructionRepository,
                userRepository, getUserProfileHelperComponent, userHelperComponent, drivingLicenseRepository);
    }


    @ParameterizedTest
    @MethodSource("saveInfoDrivingLicense$BadDataSet")
    public void saveInfoDrivingLicense$BadRequest(SaveInfoDrivingLicenseRequest request) {
        //Given
        SaveInfoDrivingLicenseResponse response = userProfileService.saveInfoDrivingLicense(TestUtils.TEST_REQUEST_ID, request);
        //When

        //Then

    }

    private static Stream<Arguments> saveInfoDrivingLicense$BadDataSet() throws ParseException {
        DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        return Stream.of(
                //categories is NULL
                Arguments.of(SaveInfoDrivingLicenseRequest.builder()
                        .userId(1)
                        .categories(
                                Set.of(
                                        DriverLicenceCategory.A,
                                        DriverLicenceCategory.B
                                )
                        )
                        .date_issue(format.parse("15.10.2022"))
                        .date_end(format.parse("15.10.2063"))
                        .build()),

                Arguments.of(SaveInfoDrivingLicenseRequest.builder()
                        .userId(1)
                        .categories(
                                Set.of(
                                        DriverLicenceCategory.A,
                                        DriverLicenceCategory.B
                                )
                        )
                        .date_issue(format.parse("15.10.2022"))
                        .date_end(format.parse("15.10.2063"))
                        .build()

                )
        );
    }

}