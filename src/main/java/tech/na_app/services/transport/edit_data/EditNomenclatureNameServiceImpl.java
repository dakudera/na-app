package tech.na_app.services.transport.edit_data;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import tech.na_app.entity.transport.Transport;
import tech.na_app.entity.transport.TransportCard;
import tech.na_app.model.ApiException;
import tech.na_app.model.ErrorObject;
import tech.na_app.model.transport.nomenclature_name.EditNomenclatureNameRequest;
import tech.na_app.model.transport.nomenclature_name.EditNomenclatureNameResponse;
import tech.na_app.repository.TransportRepository;

@Log4j2
@Service
@RequiredArgsConstructor
public class EditNomenclatureNameServiceImpl implements EditNomenclatureNameService {

    private final TransportRepository transportRepository;

    @Override
    public EditNomenclatureNameResponse editNomenclatureName(String requestId, EditNomenclatureNameRequest request) {
        try {
            Transport transport = transportRepository.findById(request.getId())
                    .orElseThrow(() -> new ApiException(404, "Not Found"));


            if (transport.getTransport_card() != null) {
                transport.getTransport_card().setNomenclature_name(request.getNomenclature_name());
            } else {
                TransportCard transport_card = TransportCard
                        .builder()
                        .nomenclature_name(request.getNomenclature_name())
                        .build();
                transport.setTransport_card(transport_card);
            }
            transportRepository.save(transport);

            return new EditNomenclatureNameResponse(new ErrorObject(0));
        } catch (ApiException e) {
            log.error(requestId + " Error: " + e.getCode() + " Message: " + e.getMessage());
            return new EditNomenclatureNameResponse(new ErrorObject(e.getCode(), e.getMessage()));
        } catch (Exception e) {
            log.error(requestId + " Message: " + e.getMessage());
            return new EditNomenclatureNameResponse(new ErrorObject(500, "Something went wrong"));
        }
    }
}
