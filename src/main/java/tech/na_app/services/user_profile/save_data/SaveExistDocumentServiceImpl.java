package tech.na_app.services.user_profile.save_data;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import tech.na_app.entity.profile.AvailableDocuments;
import tech.na_app.entity.user.User;
import tech.na_app.model.exceptions.ApiException;
import tech.na_app.model.exceptions.ErrorObject;
import tech.na_app.model.profile.ExistDocumentRequest;
import tech.na_app.model.profile.ExistDocumentResponse;
import tech.na_app.repository.AvailableDocumentsRepository;
import tech.na_app.services.user_profile.UserProfileAbs;

import java.util.Optional;

@Log4j2
@Service
@RequiredArgsConstructor
public class SaveExistDocumentServiceImpl extends UserProfileAbs implements SaveExistDocumentService {

    private final AvailableDocumentsRepository availableDocumentsRepository;

    @Override
    public ExistDocumentResponse saveExistDocument(String requestId, User user, ExistDocumentRequest request) {
        try {
            User userInfo = choosingUser(user, request.userId());

            Optional<AvailableDocuments> availableDocumentsOptional = availableDocumentsRepository.findByUserId(userInfo.getId());
            AvailableDocuments availableDocuments;
            // do update
            if (availableDocumentsOptional.isPresent()) {
                availableDocuments = availableDocumentsOptional.get();

                availableDocuments.setIpn(request.ipn());
                availableDocuments.setPassport(request.passport());
                availableDocuments.setEmployment_history(request.employment_history());
                availableDocuments.setHealth_certificate(request.health_certificate());
                availableDocuments.setMilitary_registration_doc(request.military_registration_doc());
            } else { // do save new entry

                availableDocuments = AvailableDocuments.builder()
                        .ipn(request.ipn())
                        .passport(request.passport())
                        .employment_history(request.employment_history())
                        .health_certificate(request.health_certificate())
                        .military_registration_doc(request.military_registration_doc())
                        .userId(userInfo.getId())
                        .build();
            }
            availableDocumentsRepository.save(availableDocuments);

            return new ExistDocumentResponse(new ErrorObject(0));
        } catch (ApiException e) {
            log.error(requestId + " Error: " + e.getCode() + " Message: " + e.getMessage());
            return new ExistDocumentResponse(new ErrorObject(e.getCode(), e.getMessage()));
        } catch (Exception e) {
            log.error(requestId + " Message: " + e.getMessage());
            return new ExistDocumentResponse(new ErrorObject(500, "Something went wrong"));
        }
    }
}
