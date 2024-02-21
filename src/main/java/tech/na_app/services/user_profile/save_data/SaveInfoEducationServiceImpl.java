package tech.na_app.services.user_profile.save_data;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import tech.na_app.entity.profile.Education;
import tech.na_app.entity.profile.EducationSequence;
import tech.na_app.model.ApiException;
import tech.na_app.model.ErrorObject;
import tech.na_app.model.profile.education.SaveInfoEducationRequest;
import tech.na_app.model.profile.education.SaveInfoEducationResponse;
import tech.na_app.repository.*;
import tech.na_app.services.user.UserHelperComponent;
import tech.na_app.services.user_profile.GetUserProfileHelperComponent;
import tech.na_app.utils.SequenceGeneratorService;

@Log4j2
@Service
@RequiredArgsConstructor
public class SaveInfoEducationServiceImpl implements SaveInfoEducationService {

    private final EducationRepository educationRepository;
    private final SequenceGeneratorService sequenceGeneratorService;

    @Override
    public SaveInfoEducationResponse saveInfoEducation(String requestId, SaveInfoEducationRequest request) {
        try {
            if (request.getUserId() == null) {
                throw new ApiException(400, "BAD REQUEST");
            }
            if (request.getCertificate() == null || request.getCertificate().isEmpty()) {
                throw new ApiException(400, "BAD REQUEST");
            }
            if (request.getSpecialty() == null || request.getSpecialty().isEmpty()) {
                throw new ApiException(400, "BAD REQUEST");
            }
            if (request.getAdvanced_qualification() == null || request.getAdvanced_qualification().isEmpty()) {
                throw new ApiException(400, "BAD REQUEST");
            }

            EducationSequence sequenceNumber = (EducationSequence) sequenceGeneratorService.getSequenceNumber(Education.SEQUENCE_NAME, EducationSequence.class);

            educationRepository.save(
                    Education.builder()
                            .id(sequenceNumber.getSeq())
                            .userId(request.getUserId())
                            .certificate(request.getCertificate())
                            .specialty(request.getSpecialty())
                            .advanced_qualification(request.getAdvanced_qualification())
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
