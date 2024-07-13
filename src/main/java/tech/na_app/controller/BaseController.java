package tech.na_app.controller;

import lombok.extern.log4j.Log4j2;
import tech.na_app.model.exceptions.ApiException;
import tech.na_app.utils.HelpUtil;

@Log4j2
public abstract class BaseController {
    protected <T> T handleRequest(String requestId, RequestHandler<T> handler) {
        try {
            T response = handler.handle();
            log.info(requestId + " Response: " + response);
            return response;
        } catch (ApiException e) {
            log.error(requestId + " Error: " + e.getCode() + " Message: " + e.getMessage());
            throw e;
        }
    }

    protected String generateRequestId() {
        return HelpUtil.getUUID();
    }

    @FunctionalInterface
    protected interface RequestHandler<T> {
        T handle() throws ApiException;
    }
}
