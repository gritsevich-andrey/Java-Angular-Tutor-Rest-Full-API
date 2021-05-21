package com.fitness.lessonservice.exception;

import org.apache.log4j.Logger;
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;

import java.lang.invoke.MethodHandles;
import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class ErrorAttributes extends DefaultErrorAttributes {
    private static final Logger log = Logger.getLogger(MethodHandles.lookup().lookupClass());

    @Override
    public Map<String, Object> getErrorAttributes(ServerRequest request, boolean includeStackTrace) {
        return assembleError(request);
    }

    private Map<String, Object> assembleError(ServerRequest request) {
        Map<String, Object> errorAttributes = new LinkedHashMap<>();
        Throwable error = getError(request);
        if (error instanceof OptimisticLockingFailureException) {
            errorAttributes.put("errorCode", 400);
            errorAttributes.put("errorMessage", "Version mismatch");
            log.info("Lesson Service: Optimistic Local Exception");
        } else {
            errorAttributes.put("errorCode", HttpStatus.INTERNAL_SERVER_ERROR);
            errorAttributes.put("errorMessage", "INTERNAL SERVER ERROR");
            log.info("Lesson Service: INTERNAL SERVER ERROR");
        }
        return errorAttributes;
    }
}
