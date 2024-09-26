package sda.spring.productmanagement.exception;


import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, List<String>> fieldErrors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .collect(Collectors.groupingBy(
                        FieldError::getField,
                        Collectors.mapping(FieldError::getDefaultMessage, Collectors.toList())
                ));

        List<ValidationError> errors = fieldErrors.entrySet()
                .stream()
                .map(entry -> new ValidationError(entry.getKey(), entry.getValue()))
                .toList();

        ApiError apiError =new ApiError(
                HttpStatus.BAD_REQUEST.value(),
                "Validation Error | Bad Request",
                errors
        );

        return ResponseEntity.badRequest().body(apiError);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiError> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
        String errorMessage = "Invalid Request payload";
        if (ex.getCause() instanceof UnrecognizedPropertyException unrecognizedPropertyException) {
            List<String> unrecognizedFields = Collections.singletonList(unrecognizedPropertyException.getPropertyName());

            errorMessage = "Unrecognized Field: " + String.join(", ", unrecognizedFields);
        }

        ApiError apiError =new ApiError(
                HttpStatus.BAD_REQUEST.value(),
                errorMessage,
                null
        );
        return ResponseEntity.badRequest().body(apiError);
    }
}
