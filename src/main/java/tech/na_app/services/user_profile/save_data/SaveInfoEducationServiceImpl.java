package tech.na_app.services.user_profile.save_data;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import tech.na_app.entity.profile.Education;
import tech.na_app.model.exceptions.ApiException;
import tech.na_app.model.exceptions.ErrorObject;
import tech.na_app.model.profile.education.SaveInfoEducationRequest;
import tech.na_app.model.profile.education.SaveInfoEducationResponse;
import tech.na_app.repository.EducationRepository;

@Log4j2
@Service
@RequiredArgsConstructor
public class SaveInfoEducationServiceImpl implements SaveInfoEducationService {

    private final EducationRepository educationRepository;

    @Override
    public SaveInfoEducationResponse saveInfoEducation(String requestId, SaveInfoEducationRequest request) {
        try {
            educationRepository.save(
                    Education.builder()
                            .userId(request.userId())
                            .certificate(request.certificate())
                            .specialty(request.specialty())
                            .advanced_qualification(request.advanced_qualification())
                            .build()
            );

            return new SaveInfoEducationResponse(new ErrorObject(0));
        } catch (ApiException e) {
            log.error(requestId + " Error: " + e.getCode() + " Message: " + e.getMessage());
            return new SaveInfoEducationResponse(new ErrorObject(e.getCode(), e.getMessage()));
        } catch (Exception e) {
            log.error(requestId + " Message: " + e.getMessage());
            return new SaveInfoEducationResponse(new ErrorObject(500, "Something went wrong"));
        }
    }
}
