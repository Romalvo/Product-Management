package sda.spring.productmanagement.exception;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ApiError {

    private int status;
    private String message;
    private List<ValidationError> errors;
}
