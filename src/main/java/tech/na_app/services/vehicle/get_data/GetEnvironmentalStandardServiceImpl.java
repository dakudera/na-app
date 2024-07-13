package tech.na_app.services.vehicle.get_data;


import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import tech.na_app.model.enums.EnvironmentalStandard;
import tech.na_app.model.exceptions.ApiException;
import tech.na_app.model.exceptions.ErrorObject;
import tech.na_app.model.vehicle.GetEnvironmentalStandardResponse;

import java.util.ArrayList;
import java.util.List;

@Log4j2
@Service
public class GetEnvironmentalStandardServiceImpl implements GetEnvironmentalStandardService {


    public GetEnvironmentalStandardResponse getEnvironmentalStandard(String requestId) {
        try {

            List<GetEnvironmentalStandardResponse.GetEnvironmentalStandard> standards = new ArrayList<>();

            for (var value : EnvironmentalStandard.values()) {
                standards.add(
                        GetEnvironmentalStandardResponse.GetEnvironmentalStandard
                                .builder()
                                .standard(value)
                                .name(value.getValue())
                                .build()
                );
            }

            return GetEnvironmentalStandardResponse.builder()
                    .standards(standards)
                    .build();
        } catch (ApiException e) {
            log.error(requestId + " Error: " + e.getCode() + " Message: " + e.getMessage());
            return new GetEnvironmentalStandardResponse(new ErrorObject(e.getCode(), e.getMessage()));
        } catch (Exception e) {
            log.error(requestId + " Message: " + e.getMessage());
            return new GetEnvironmentalStandardResponse(new ErrorObject(500, "Something went wrong"));
        }
    }

}
