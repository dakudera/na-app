package tech.na_app.services.vehicle.get_data;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import tech.na_app.model.enums.Fuel;
import tech.na_app.model.exceptions.ApiException;
import tech.na_app.model.exceptions.ErrorObject;
import tech.na_app.model.vehicle.GetFuelResponse;

import java.util.ArrayList;
import java.util.List;

@Log4j2
@Service
public class GetFuelServiceImpl implements GetFuelService {

    @Override
    public GetFuelResponse getFuel(String requestId) {
        try {

            List<GetFuelResponse.GetFuel> fuels = new ArrayList<>();

            for (var value : Fuel.values()) {
                fuels.add(
                        GetFuelResponse.GetFuel.builder()
                                .fuel(value)
                                .name(value.getValue())
                                .build()
                );
            }

            return GetFuelResponse.builder()
                    .fuels(fuels)
                    .build();
        } catch (ApiException e) {
            log.error(requestId + " Error: " + e.getCode() + " Message: " + e.getMessage());
            return new GetFuelResponse(new ErrorObject(e.getCode(), e.getMessage()));
        } catch (Exception e) {
            log.error(requestId + " Message: " + e.getMessage());
            return new GetFuelResponse(new ErrorObject(500, "Something went wrong"));
        }
    }
}
