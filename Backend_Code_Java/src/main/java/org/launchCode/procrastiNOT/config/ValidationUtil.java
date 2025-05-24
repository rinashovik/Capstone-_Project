package org.launchCode.procrastiNOT.config;
import org.springframework.validation.Errors;

import java.util.HashMap;
import java.util.Map;

public class ValidationUtil {
    public static Map<String, String> getErrorsMap(Errors errors) {
        Map<String, String> errorMap = new HashMap<>();
        errors.getFieldErrors().forEach(error -> errorMap.put(error.getField(), error.getDefaultMessage()));
        return errorMap;
    }
}